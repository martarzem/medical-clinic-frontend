package com.crud.medicalclinicfrontend.form;

import com.crud.medicalclinicfrontend.domain.Office;
import com.crud.medicalclinicfrontend.service.OfficeService;
import com.crud.medicalclinicfrontend.view.OfficeView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import lombok.Data;

@Data
public class OfficeForm extends FormLayout {
    private OfficeView officeView;
    private OfficeService officeService = OfficeService.getInstance();

    private TextField number = new TextField("Number");
    private TextField description = new TextField("Description");

    private Button update = new Button("Update");
    private Button delete = new Button("Delete");

    private Binder<Office> binder = new Binder<>(Office.class);

    private Office office = new Office();

    public OfficeForm(OfficeView officeView) {
        this.officeView = officeView;

        HorizontalLayout buttons = new HorizontalLayout(update, delete);
        update.addClickListener(event -> updateOffice());
        delete.addClickListener(event -> deleteOffice());

        add(number, description, buttons);

        binder.forField(number).withConverter(new StringToIntegerConverter(""))
                .bind(Office::getNumber, Office::setNumber);
        binder.bindInstanceFields(this);
    }

    private void updateOffice() {
        Office office = binder.getBean();
        officeService.updateOffice(office);
        officeView.refresh();
    }

    private void deleteOffice() {
        Office office = binder.getBean();
        officeService.deleteOffice(office);
        officeView.refresh();
    }

    public void setOffice(Office office) {
        binder.setBean(office);
    }
}
