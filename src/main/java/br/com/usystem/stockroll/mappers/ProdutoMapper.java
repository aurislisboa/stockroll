package br.com.usystem.stockroll.mappers;

import org.springframework.stereotype.Component;

import br.com.usystem.stockroll.dto.ProdutoForm;
import br.com.usystem.stockroll.model.Produto;

@Component
public class ProdutoMapper {
  
  public Produto toModel(ProdutoForm form) {
      if(form == null) throw new IllegalArgumentException();

      var model = new Produto();
          model.setId(form.getId());
          model.setCodigoBarra(form.getCodigoBarra());
          model.setNome(form.getNome());

      return model;
  }

  public ProdutoForm toForm(Produto model) {
      if(model == null) throw new IllegalArgumentException();

      var form = new ProdutoForm();
          form.setId(model.getId());
          form.setCodigoBarra(model.getCodigoBarra());
          form.setNome(model.getNome());

    return form;
  }

}
