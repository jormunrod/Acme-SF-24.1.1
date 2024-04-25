
package acme.features.auditor.codeAudit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.audits.CodeAudit;
import acme.entities.audits.Mark;
import acme.roles.Auditor;

@Service
public class AuditorCodeAuditListService extends AbstractService<Auditor, CodeAudit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuditorCodeAuditRepository repository;

	// Constructors -----------------------------------------------------------


	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Auditor.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Collection<CodeAudit> objects;
		List<Mark> marks;

		objects = this.repository.findAllCodeAuditsByAuditorId(super.getRequest().getPrincipal().getActiveRoleId());

		for (CodeAudit object : objects) {
			marks = this.repository.findAllAuditRecordsByCodeAuditId(object.getId()).stream().map(ar -> ar.getMark()).toList();
			if (marks.isEmpty())
				continue;
			Map<Mark, Integer> frequencies = new HashMap<>();
			for (Mark mark : marks)
				frequencies.put(mark, frequencies.getOrDefault(mark, 0) + 1);

			List<Mark> modes = new ArrayList<>();
			int maxFrequency = 0;
			for (Map.Entry<Mark, Integer> entry : frequencies.entrySet()) {
				int frequency = entry.getValue();
				if (frequency > maxFrequency) {
					maxFrequency = frequency;
					modes.clear();
					modes.add(entry.getKey());
				} else if (frequency == maxFrequency)
					modes.add(entry.getKey());
			}
			modes.sort(Comparator.naturalOrder());
			object.setMark(modes.get(modes.size() - 1));
		}

		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final CodeAudit object) {
		assert object != null;

		Dataset dataset;

		dataset = super.unbind(object, "code", "execution", "type", "mark", "isPublished");

		super.getResponse().addData(dataset);
	}

}
