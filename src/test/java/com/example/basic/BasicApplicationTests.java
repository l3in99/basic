package com.example.basic;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.basic.entity.Player;
import com.example.basic.entity.Team;
import com.example.basic.repository.PlayerRepository;
import com.example.basic.repository.TeamRepository;

@SpringBootTest
class BasicApplicationTests {
	@Autowired PlayerRepository pr;
	@Autowired TeamRepository tr;

	@Test
	void contextLoads() {
		Team team = new Team();
		team.setTeamId(1);
		Player p = new Player();
		p.setPlayerId(2);
		p.setPlayerName("회원2");
		p.setTeam(team);
		pr.save(p);
	}

	@Test
	void TeamRepositorytest() {
		Team t = new Team();
		t.setTeamId(1);
		t.setTeamName("A팀");
		tr.save(t);
	}

	@Test
	void test() {
		Optional<Player> opt = pr.findById(2);
		if(opt.isPresent()) {
			Player p = opt.get();
			System.out.println(p.getPlayerName());
			Team t = p.getTeam();
			System.out.println(t.getTeamName());
		}
	}

	@Test
	@Transactional
	void test2() {
		Optional<Team> opt = tr.findById(1);
		if(opt.isPresent()) {
			Team t = opt.get();
			System.out.println(t.getTeamName());
			List<Player> playerList = t.getPlayerList();
			for(Player p : playerList) {
				System.out.println(p.getPlayerName());
			}
		}
	}
}
