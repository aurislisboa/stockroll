package br.com.usystem.stockroll.mappers;

import org.springframework.stereotype.Component;

import br.com.usystem.stockroll.dto.MotivoForm;
import br.com.usystem.stockroll.model.Motivo;

@Component
public class MotivoMapper {

  public Motivo toModel(MotivoForm form) {

    if(form == null) throw new IllegalArgumentException();

    var model = new Motivo();
        model.setId(form.getId());
        model.setNome(form.getNome());
    return model;
  }

  public MotivoForm toForm(Motivo model) {

    if(model == null) throw new IllegalArgumentException();

    var form = new MotivoForm();
        form.setId(model.getId());
        form.setNome(model.getNome());
    return form;
  }
  
}
