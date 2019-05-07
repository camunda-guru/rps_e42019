package com.daimler.ecommerce.eapplication.parts;

import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.css.swt.theme.ITheme;
import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class ThemePart {
	@Inject
	private IThemeEngine themeEngine;
	@PostConstruct
	public void create(Composite parent) {
		try
		{
		
		ListViewer lv = new ListViewer(parent, SWT.NONE);
		lv.setContentProvider(new ArrayContentProvider());
		lv.setInput(themeEngine.getThemes().stream() //
				.map(ITheme::getLabel).collect(Collectors.toList()));
		
		lv.addSelectionChangedListener(e -> {
			
			ISelection sel = e.getSelection();
			
			if (sel instanceof IStructuredSelection && !sel.isEmpty()) {
			Object selectedElement =
			((IStructuredSelection) sel).getFirstElement();
			for (ITheme theme : themeEngine.getThemes()) {
			
			if (selectedElement.equals(theme.getLabel())) {
			themeEngine.setTheme(theme, false);
			}
				
			}
			}
			
			});
		
		}
		catch(NullPointerException ex)
		{
			
		}
	}
}
