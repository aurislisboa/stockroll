package br.com.usystem.stockroll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.usystem.stockroll.model.Local;
import br.com.usystem.stockroll.repository.LocalRepository;

@Service
public class LocalService {

  @Autowired
  LocalRepository localRepository;

  
  public List<Local> listarTodosQuiosques() {
      return  localRepository.listarTodosQuiosques();
  }

  public List<Local> listarTodosLocais() {
    return localRepository.findAll();
  }


}
