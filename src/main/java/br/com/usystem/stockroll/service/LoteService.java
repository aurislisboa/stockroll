package br.com.usystem.stockroll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.usystem.stockroll.model.Lote;
import br.com.usystem.stockroll.repository.LoteRepository;

@Service
public class LoteService {
  

  @Autowired
  private LoteRepository loteRepository;



  public List<Lote> buscarTodosLotes() {
      return loteRepository.findByQuantidadeGreaterThan(0);
  }


  public Lote buscarLotePorId(Integer id) {
      return loteRepository.getReferenceById(id);
  }


  public Lote salvarLote(Lote lote) {
      return loteRepository.save(lote);
  }


  public void excluirLotePorId(Integer id) {
      loteRepository.deleteById(id);
  }

















} // End.
