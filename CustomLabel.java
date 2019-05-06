package com.daimler.ecommerce.eapplication.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.osgi.service.log.LogService;

public class CustomLabel {
	@Inject
	private MWindow window;
	
	@Optional
	@Inject
	private LogService logService;	
	private Label label;
	
	@Inject	
	@Named("math.random")
	private Object random;
	
	private Button button;
	
	@Inject
	private UISynchronize ui;
	
	@PostConstruct
	public void create(Composite parent) {
		
		button = new Button(parent, SWT.PUSH);
		button.setText("Do not push");
		button.addSelectionListener(new SelectionListener() {
		@Override
		public void widgetSelected(SelectionEvent e) {
		button.setEnabled(false);
		new Job("Button Pusher") {
		@Override
		protected IStatus run(IProgressMonitor monitor) {
			ui.asyncExec(() -> button.setEnabled(true));
			return Status.OK_STATUS;
			}
			}.schedule(10);
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
			});
		
	label = new Label(parent, SWT.NONE);
	
	label.setText(window.getLabel() + " " + random);
	
	label.setData("org.eclipse.e4.ui.css.id",
			"lblTxt");
	//label.setText("Product");
	//label.setText(window.getLabel());
	logService.log(LogService.LOG_ERROR, "Logging Product");
	
	}
	
	@Focus
	public void onFocus() {
	label.setFocus();
	}
	/*
	@Inject
	@Optional
	public void setSelection(@Named(IServiceConstants.ACTIVE_SELECTION)	Object selection) {
	if (selection != null) {
	label.setText(selection.toString());
	}
	}*/
	
	@Inject
	@Optional
	public void receiveEvent(@UIEventTopic("category/cat") String data) {
	label.setText(data+ " " + random);
	}
	

	
	
	
}
