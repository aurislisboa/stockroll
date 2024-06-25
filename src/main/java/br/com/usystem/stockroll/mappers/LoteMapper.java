package br.com.usystem.stockroll.mappers;

import org.springframework.stereotype.Component;

import br.com.usystem.stockroll.dto.LoteForm;
import br.com.usystem.stockroll.model.Lote;

@Component
public class LoteMapper {

  public Lote toModel(LoteForm form) {
      if(form == null) throw new IllegalArgumentException();

      var model = new Lote();
          model.setId(form.getId());
          model.setProduto(form.getProduto());
          model.setVencimento(form.getVencimento());
          model.setQuantidade(form.getQuantidade());
          model.setValorUnitario(form.getValorUnitario());

      return model;
  }

  public LoteForm toForm(Lote model) {
      if(model == null) throw new IllegalArgumentException();

      var form = new LoteForm();
          form.setId(model.getId());
          form.setProduto(model.getProduto());
          form.setVencimento(model.getVencimento());
          form.setQuantidade(model.getQuantidade());
          form.setValorUnitario(model.getValorUnitario());

      return form;
  }
  
}
