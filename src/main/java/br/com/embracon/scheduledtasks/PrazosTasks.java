package br.com.embracon.scheduledtasks;

import br.com.embracon.model.Grupo;
import br.com.embracon.repository.GrupoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class PrazosTasks {

    @Autowired
    private GrupoRepository grupoRepository;

    private static final Logger Log = LoggerFactory.getLogger(PrazosTasks.class);

    @Scheduled(fixedRate = 30000)
    public void updatePrazoComercial(){
        LocalDate hoje = LocalDate.now();

        verificaGruposAtivos().forEach(g -> {
            System.out.println(g.getVencimento().getMonth());
            System.out.println(g.getVencimento().getMonthValue());

            if(g.getVencimento().getDayOfMonth() >= hoje.getDayOfMonth() &&
            g.getVencimento().getMonthValue() <= hoje.getMonthValue()){

                g.setPrazoComercial(g.getPrazoComercial() - 1);
                g.setVencimento(g.getVencimento().plusMonths(1L));

                if(g.getPrazoComercial() == 0){
                    g.setStatus(false);
                }

                grupoRepository.save(g);
            }
        });
    }


    private List<Grupo> verificaGruposAtivos() {
        var grupos =  grupoRepository.findAllByStatus(true);
        return grupos;
    }
}
