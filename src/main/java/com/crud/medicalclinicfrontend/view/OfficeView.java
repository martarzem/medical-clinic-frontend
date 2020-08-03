package com.crud.medicalclinicfrontend.view;

import com.crud.medicalclinicfrontend.domain.Office;
import com.crud.medicalclinicfrontend.form.OfficeForm;
import com.crud.medicalclinicfrontend.service.OfficeService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("medical_clinic/office")
public class OfficeView extends VerticalLayout {
    private OfficeService officeService = OfficeService.getInstance();
    private Grid officeGrid = new Grid<>(Office.class);
    private TextField filter = new TextField();

    private OfficeForm officeForm = new OfficeForm(this);

    public OfficeView() {
        filter.setPlaceholder("Filter by description...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(event -> findByDescription());
        officeGrid.setColumns("id", "number", "description");

        HorizontalLayout content = new HorizontalLayout(officeGrid, officeForm);
        content.setSizeFull();
        officeGrid.setSizeFull();
        add(filter, content);

        setSizeFull();
        refresh();

        officeGrid.asSingleSelect().addValueChangeListener(event ->
                officeForm.setOffice((Office) officeGrid.asSingleSelect().getValue()));
    }

    public void refresh() {
        officeGrid.setItems(officeService.getAll());
    }

    public void findByDescription() {
        officeGrid.setItems(officeService.findByDescription(filter.getValue()));
    }
}
