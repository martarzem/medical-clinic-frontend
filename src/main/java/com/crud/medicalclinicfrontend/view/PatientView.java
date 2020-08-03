package com.crud.medicalclinicfrontend.view;

import com.crud.medicalclinicfrontend.domain.Patient;
import com.crud.medicalclinicfrontend.form.PatientForm;
import com.crud.medicalclinicfrontend.service.PatientService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;


@Route("medical_clinic/patient")
public class PatientView extends VerticalLayout {
    private PatientService patientService = PatientService.getInstance();
    private Grid patientGrid = new Grid<>(Patient.class);
    private TextField filter = new TextField();

    private PatientForm patientForm = new PatientForm(this);

    public PatientView() {
        filter.setPlaceholder("Filter by lastname...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(event -> findByLastname());
        patientGrid.setColumns("id", "name", "lastname", "pesel");

        HorizontalLayout content = new HorizontalLayout(patientGrid, patientForm);
        content.setSizeFull();
        patientGrid.setSizeFull();
        add(filter, content);

        setSizeFull();
        refresh();

        patientGrid.asSingleSelect().addValueChangeListener(event ->
                patientForm.setPatient((Patient) patientGrid.asSingleSelect().getValue()));
    }

    public void refresh() {
        patientGrid.setItems(patientService.getAll());
    }

    public void findByLastname() {
        patientGrid.setItems(patientService.findByLastname(filter.getValue()));
    }
}
