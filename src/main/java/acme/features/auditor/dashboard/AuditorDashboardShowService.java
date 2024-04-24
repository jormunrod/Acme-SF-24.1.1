
package acme.features.auditor.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.forms.AuditorDashboard;
import acme.roles.Auditor;

@Service
public class AuditorDashboardShowService extends AbstractService<Auditor, AuditorDashboard> {

	@Autowired
	private AuditorDashboardRepository repository;


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		AuditorDashboard dashboard;
		int auditorId;
		Integer totalNumberOfStaticCodeAudits;
		Integer totalNumberOfDynamicCodeAudits;

		Double averageNumberOfAuditRecord;
		Double deviationNumberOfAuditRecord;
		Integer minimumNumberOfAuditRecord;
		Integer maximumNumberOfAuditRecord;

		Double averageTimeOfPeriodInAuditRecord;
		Double deviationTimeOfPeriodInAuditRecord;
		Integer minimumTimeOfPeriodInAuditRecord;
		Integer maximumTimeOfPeriodInAuditRecord;

		auditorId = super.getRequest().getPrincipal().getActiveRoleId();

		totalNumberOfStaticCodeAudits = this.repository.totalNumberOfStaticCodeAudits(auditorId);
		totalNumberOfDynamicCodeAudits = this.repository.totalNumberOfDynamicCodeAudits(auditorId);

		averageNumberOfAuditRecord = this.repository.averageNumberOfAuditRecord(auditorId);
		deviationNumberOfAuditRecord = this.repository.deviationNumberOfAuditRecord(auditorId);
		minimumNumberOfAuditRecord = this.repository.minimumNumberOfAuditRecord(auditorId);
		maximumNumberOfAuditRecord = this.repository.maximumNumberOfAuditRecord(auditorId);

		averageTimeOfPeriodInAuditRecord = this.repository.averageTimeOfPeriodInAuditRecord(auditorId);
		deviationTimeOfPeriodInAuditRecord = this.repository.deviationTimeOfPeriodInAuditRecord(auditorId);
		minimumTimeOfPeriodInAuditRecord = this.repository.minimumTimeOfPeriodInAuditRecord(auditorId);
		maximumTimeOfPeriodInAuditRecord = this.repository.maximumTimeOfPeriodInAuditRecord(auditorId);

		dashboard = new AuditorDashboard();
		dashboard.setTotalNumberOfStaticCodeAudits(totalNumberOfStaticCodeAudits);
		dashboard.setTotalNumberOfDynamicCodeAudits(totalNumberOfDynamicCodeAudits);

		dashboard.setAverageNumberOfAuditRecord(averageNumberOfAuditRecord);
		dashboard.setDeviationNumberOfAuditRecord(deviationNumberOfAuditRecord);
		dashboard.setMinimumNumberOfAuditRecord(minimumNumberOfAuditRecord);
		dashboard.setMaximumNumberOfAuditRecord(maximumNumberOfAuditRecord);

		dashboard.setAverageTimeOfPeriodInAuditRecord(averageTimeOfPeriodInAuditRecord);
		dashboard.setDeviationTimeOfPeriodInAuditRecord(deviationTimeOfPeriodInAuditRecord);
		dashboard.setMinimumTimeOfPeriodInAuditRecord(minimumTimeOfPeriodInAuditRecord);
		dashboard.setMaximumTimeOfPeriodInAuditRecord(maximumTimeOfPeriodInAuditRecord);

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final AuditorDashboard object) {
		Dataset dataset;

		dataset = super.unbind(object, "totalNumberOfStaticCodeAudits", "totalNumberOfDynamicCodeAudits", "averageNumberOfAuditRecord", "deviationNumberOfAuditRecord", "minimumNumberOfAuditRecord", "maximumNumberOfAuditRecord",
			"averageTimeOfPeriodInAuditRecord", "deviationTimeOfPeriodInAuditRecord", "minimumTimeOfPeriodInAuditRecord", "maximumTimeOfPeriodInAuditRecord");

		super.getResponse().addData(dataset);
	}
}
