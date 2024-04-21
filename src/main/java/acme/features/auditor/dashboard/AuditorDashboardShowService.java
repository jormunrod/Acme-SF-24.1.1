
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

		totalNumberOfStaticCodeAudits = this.repository.totalNumberOfStaticCodeAudits();
		totalNumberOfDynamicCodeAudits = this.repository.totalNumberOfDynamicCodeAudits();

		averageNumberOfAuditRecord = this.repository.averageNumberOfAuditRecord();
		deviationNumberOfAuditRecord = this.repository.deviationNumberOfAuditRecord();
		minimumNumberOfAuditRecord = this.repository.minimumNumberOfAuditRecord();
		maximumNumberOfAuditRecord = this.repository.maximumNumberOfAuditRecord();

		averageTimeOfPeriodInAuditRecord = this.repository.averageTimeOfPeriodInAuditRecord();
		deviationTimeOfPeriodInAuditRecord = this.repository.deviationTimeOfPeriodInAuditRecord();
		minimumTimeOfPeriodInAuditRecord = this.repository.minimumTimeOfPeriodInAuditRecord();
		maximumTimeOfPeriodInAuditRecord = this.repository.maximumTimeOfPeriodInAuditRecord();

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
