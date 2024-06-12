// package br.com.usystem.stockroll.mappers;

// import br.com.usystem.stockroll.dto.EstoqueDTO;
// import br.com.usystem.stockroll.dto.EstoqueIdDTO;
// import br.com.usystem.stockroll.model.Estoque;
// import br.com.usystem.stockroll.model.EstoqueId;
// import br.com.usystem.stockroll.model.Local;
// import br.com.usystem.stockroll.model.Lote;

// public class EstoqueConverter {
  
//   public static EstoqueIdDTO toDto(EstoqueId estoqueId) {

//     return new EstoqueIdDTO(estoqueId.getLocal().getId(), 
//               estoqueId.getLote().getId());

//   }

//   public static EstoqueId toEntity(EstoqueIdDTO estoqueIdDTO) {
//     Local local = new Local();
//     local.setId(estoqueIdDTO.getLocalId());

//     Lote lote = new Lote();
//     lote.setId(estoqueIdDTO.getLoteId());

//     return new EstoqueId(local, lote);
//   }


//   public static EstoqueDTO toDto(Estoque estoque) {
//     return new EstoqueDTO(
//         estoque.getId().getLocal().getId(),
//         estoque.getId().getLote().getId(),
//         estoque.getQuantidade()
//     );
//   }


//   public static Estoque toEntity(EstoqueDTO estoqueDTO) {
//     EstoqueId estoqueId = toEntity(new EstoqueIdDTO(
//         estoqueDTO.getLocalId(), 
//         estoqueDTO.getLoteId()
//     ));

//     return new Estoque(estoqueId, estoqueDTO.getQuantidade());
//   }
// }
  