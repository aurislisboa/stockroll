// package br.com.usystem.stockroll.mappers;

// import org.springframework.stereotype.Component;

// import br.com.usystem.stockroll.dto.EstoqueIdForm;
// import br.com.usystem.stockroll.model.EstoqueId;
// import br.com.usystem.stockroll.model.Local;
// import br.com.usystem.stockroll.model.Lote;

// @Component
// public class EstoqueIdMapper {
  
//   public EstoqueId toModel(EstoqueIdForm form) {
    
//     var model = new EstoqueId();

//     // Local local = form.getLocal();
//     // Lote lote = form.getLote();
    
//     // EstoqueId estoqueId = new EstoqueId(local, lote);

//     form.setLocal(form.getLocal());
//     form.setLote(form.getLote());

//     return model;
//   }


//   public EstoqueIdForm toForm(EstoqueId model) {

//     var form = new EstoqueIdForm();
//     form.setLocal(model.getLocal());
//     form.setLote(model.getLote());


//     return form;
//   }


// }
