package br.com.usystem.stockroll.mappers;

import org.springframework.stereotype.Component;

import br.com.usystem.stockroll.dto.LocalForm;
import br.com.usystem.stockroll.model.Local;

@Component
public class LocalMapper {
  

  public Local toModel(LocalForm form) {

    if(form == null) throw new IllegalArgumentException();

    var model = new Local();
        model.setId(form.getId());
        model.setNome(form.getNome());
    return model;
  }


  
  public LocalForm toForm(Local model) {

    if(model == null) throw new IllegalArgumentException();

    var form = new LocalForm();
        form.setId(model.getId());
        form.setNome(model.getNome());
    return form;
  }

}
