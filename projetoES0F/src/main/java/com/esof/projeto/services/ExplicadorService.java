package com.esof.projeto.services;

import com.esof.projeto.models.Cadeira;
import com.esof.projeto.models.Curso;
import com.esof.projeto.models.Faculdade;
import com.esof.projeto.models.Disponibilidade;
import com.esof.projeto.models.Explicador;
import com.esof.projeto.models.Universidade;
import com.esof.projeto.repositories.UniversidadeRepo;
import com.esof.projeto.services.filters.explicador.FilterExplicadorObject;
import com.esof.projeto.services.filters.explicador.FilterExplicadorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esof.projeto.repositories.CursoRepo;
import com.esof.projeto.repositories.ExplicadorRepo;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ExplicadorService {

    private ExplicadorRepo explicadorRepo;
    private CursoRepo cursoRepo;
    private UniversidadeRepo universidadeRepo;

    @Autowired
    private FilterExplicadorServices filterService;

    @Autowired
    public ExplicadorService(ExplicadorRepo explicadorRepo, CursoRepo cursoRepo,UniversidadeRepo universidadeRepo) {
        this.cursoRepo = cursoRepo;
        this.explicadorRepo = explicadorRepo;
        this.universidadeRepo = universidadeRepo;
    }

    public Optional<Explicador> createExplicador(Explicador explicador) {
        Optional<Explicador> optionalExplicador = this.explicadorRepo.findByName(explicador.getName());
        if (optionalExplicador.isPresent()) {
            return Optional.empty();
        }
        Explicador createdExplicador = this.explicadorRepo.save(explicador);
        return Optional.of(createdExplicador);
    }

    public Optional<Explicador> createExplicadorUni(Explicador explicador, String universidade) {
        Optional<Explicador> optionalExplicador = this.explicadorRepo.findByName(explicador.getName());
        Optional<Universidade> optionalUniversidade = this.universidadeRepo.findByName(universidade);
        if (optionalExplicador.isEmpty() || optionalUniversidade.isEmpty()) {
            return Optional.empty();
        }
        for (Faculdade f:optionalUniversidade.get().getFaculdades()) {
            for (Curso c:f.getCursos()) {
                for (Cadeira ca:c.getCadeiras()) {
                    if(!explicador.getCadeiras().contains(ca)){
                        explicador.addCadeira(ca);
                    }
                }

            }

        }
        Explicador createdExplicador = this.explicadorRepo.save(explicador);
        return Optional.of(createdExplicador);
    }

    public Set<Explicador> findAll() {
        Set<Explicador> explicadors = new HashSet<>();
        for (Explicador explicador : this.explicadorRepo.findAll()) {
            explicadors.add(explicador);
        }
        return explicadors;
    }

    public Optional<Explicador> findById(Long id) {
        return this.explicadorRepo.findById(id);
    }

    public Optional<Explicador> findByName(String name) {
        return this.explicadorRepo.findByName(name);
    }

    public Optional<Explicador> updateCurso(Long id, Explicador explicador) {
        if (id != null) {
            Optional<Curso> optionalCurso = cursoRepo.findById(id);
            if (optionalCurso.isPresent()) {
                Optional<Explicador> optionalExplicador = explicadorRepo.findById(explicador.getId());
                if (optionalExplicador.isPresent()) {
                    Set<Cadeira> setCadeirasexplicador = optionalExplicador.get().getCadeiras();
                    Set<Cadeira> setCadeirascurso = optionalCurso.get().getCadeiras();
                    for (Cadeira ce : setCadeirasexplicador) {
                        for (Cadeira cc : setCadeirascurso) {
                            if (!ce.getId().equals(cc.getId())) {
                                optionalExplicador.get().addCadeira(cc);
                            }
                        }
                    }
                    return optionalExplicador;
                }
            }
        }
        return Optional.empty();
    }

    public Optional<Explicador> updateDisciplina(Explicador explicador) {
        if(explicador.getDisponibilidades()!=null){
            Set<Disponibilidade> disponibilidades = explicador.getDisponibilidades();
            if (explicador.getName()!=null){
                Optional<Explicador> explicadorOptional = explicadorRepo.findByName(explicador.getName());
                if (explicadorOptional.isPresent()){
                    if(explicadorOptional.get().getDisponibilidades().isEmpty()){
                        for (Disponibilidade dd:disponibilidades) {
                            explicadorOptional.get().addDisponibilidade(dd);
                        }
                    }else{
                        for (Disponibilidade dd:disponibilidades) {
                            for (Disponibilidade de : explicadorOptional.get().getDisponibilidades()){
                                if(!dd.getDiaDaSemana().equals(de.getDiaDaSemana()) || !dd.getInicio().equals(de.getInicio()) || !dd.getFim().equals(de.getFim())){
                                    if(dd.getDiaDaSemana().equals(de.getDiaDaSemana())){
                                        if(dd.getInicio().isAfter(dd.getFim())){
                                            LocalTime aux = dd.getInicio();
                                            dd.setInicio(dd.getFim());
                                            dd.setFim(aux);
                                        }
                                        if(dd.getInicio().isBefore(de.getFim()) && dd.getInicio().isAfter(de.getInicio())){dd.setInicio(de.getFim());}
                                        if(dd.getFim().isAfter(de.getInicio()) && dd.getFim().isBefore(de.getFim())){dd.setFim(de.getInicio());}
                                    }
                                    explicadorOptional.get().addDisponibilidade(dd);
                                }
                            }
                        }
                    }
                    return explicadorOptional;
                }
            }
        }
        return Optional.empty();
    }

    public Set<Explicador> filterExplicador(Map<String, String> searchParams) {

        FilterExplicadorObject filterExplicadorObject =new FilterExplicadorObject(searchParams);
        Set<Explicador> explicadores=this.findAll();

        return this.filterService.filter(explicadores, filterExplicadorObject);
    }
}
