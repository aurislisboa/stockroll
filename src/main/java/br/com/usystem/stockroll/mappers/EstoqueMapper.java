// package br.com.usystem.stockroll.mappers;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;

// import br.com.usystem.stockroll.dto.EstoqueDTO;
// import br.com.usystem.stockroll.model.Estoque;
// import br.com.usystem.stockroll.model.EstoqueId;
// import br.com.usystem.stockroll.model.Local;
// import br.com.usystem.stockroll.model.Lote;

// @Component
// public class EstoqueMapper {

  
//   public Estoque toModel(EstoqueDTO form) {

//     var model = new Estoque();

//     // Local local = form.getId().getLocal();
//     // Lote lote = form.getId().getLote();
//     // EstoqueForm estoqueId = new EstoqueId(form.getId());

//     model.setId(form.getId());
//     model.setQuantidade(form.getQuantidade());

//     return model;
//   }



//   public EstoqueDTO toForm(Estoque model) {

//     var form = new EstoqueDTO();

//     Local local = model.getId().getLocal();
//     Lote lote = model.getId().getLote();
//     EstoqueId estoqueId = new EstoqueId(local, lote);
    
//     form.setId(estoqueId);
//     form.setQuantidade(model.getQuantidade());


//     return form;
//   }
  
// }
