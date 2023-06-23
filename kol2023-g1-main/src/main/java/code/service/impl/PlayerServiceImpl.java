package code.service.impl;

import code.model.Team;
import code.model.exceptions.InvalidPlayerIdException;
import code.model.exceptions.InvalidTeamIdException;
import code.repository.TeamRepository;
import code.service.PlayerService;
import code.model.Player;
import code.model.PlayerPosition;
import code.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Player> listAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player findById(Long id) {
        return playerRepository.findById(id).orElseThrow(InvalidPlayerIdException::new);
    }

    @Override
    public Player create(String name, String bio, Double pointsPerGame, PlayerPosition position, Long team) {
        return playerRepository.save(new Player(name, bio, pointsPerGame, position,
                teamRepository.findById(team).orElseThrow(InvalidTeamIdException::new)));
    }

    @Override
    public Player update(Long id, String name, String bio, Double pointsPerGame, PlayerPosition position, Long team) {
        Player player = playerRepository.findById(id).orElseThrow(InvalidPlayerIdException::new);
        Team newTeam = teamRepository.findById(team).orElseThrow(InvalidTeamIdException::new);
        player.setName(name);
        player.setBio(bio);
        player.setPointsPerGame(pointsPerGame);
        player.setPosition(position);
        player.setTeam(newTeam);
        return playerRepository.save(player);
    }

    @Override
    public Player delete(Long id) {
        Player player = playerRepository.findById(id).orElseThrow(InvalidPlayerIdException::new);
        playerRepository.deleteById(id);
        return player;
    }

    @Override
    public Player vote(Long id) {
        Player player = playerRepository.findById(id).orElseThrow(InvalidPlayerIdException::new);
        player.setVotes(player.getVotes() + 1);
        return playerRepository.save(player);
    }

    @Override
    public List<Player> listPlayersWithPointsLessThanAndPosition(Double pointsPerGame, PlayerPosition position) {
        if (pointsPerGame != null && position != null) {
            return playerRepository.findAllByPointsPerGameLessThanAndPosition(pointsPerGame, position);
        } else if (pointsPerGame != null) {
            return playerRepository.findAllByPointsPerGameLessThan(pointsPerGame);
        } else if (position != null) {
            return playerRepository.findAllByPosition(position);
        } else {
            return playerRepository.findAll();
        }
    }
}
