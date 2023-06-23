package code.service.impl;

import code.model.Team;
import code.model.exceptions.InvalidTeamIdException;
import code.repository.TeamRepository;
import code.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team findById(Long id) {
        return teamRepository.findById(id).orElseThrow(InvalidTeamIdException::new);
    }

    @Override
    public List<Team> listAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team create(String name) {
        return teamRepository.save(new Team(name));
    }
}
