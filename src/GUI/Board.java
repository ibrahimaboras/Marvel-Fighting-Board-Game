package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.text.AttributeSet.ColorAttribute;

import engine.Game;
import engine.Player;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import model.abilities.AreaOfEffect;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.Effect;
import model.world.Champion;
import model.world.Condition;
import model.world.Cover;
import model.world.Direction;
import model.world.Hero;
import model.world.Villain;

public class Board extends JFrame implements MouseListener {

	Game game;

	Champion p1c1;
	Champion p1c2;
	Champion p1c3;

	Champion p2c1;
	Champion p2c2;
	Champion p2c3;

	Boolean hit = false;
	Boolean hit2 = false;
	Boolean hit3 = false;

	Boolean direction1 = false;
	Boolean direction2 = false;
	Boolean direction3 = false;

	JButton exit;

	JButton move;
	JButton attack;
	JButton castability;
	JButton ability1;
	JButton ability2;
	JButton ability3;
	JButton leaderability;
	JButton endturn;

	JButton cancel;
	JButton cancel1;
	JButton cancel2;
	JButton cancel3;

	JButton up_move;
	JButton down_move;
	JButton right_move;
	JButton left_move;

	JButton up_attack;
	JButton down_attack;
	JButton right_attack;
	JButton left_attack;

	JButton up_ability;
	JButton down_ability;
	JButton right_ability;
	JButton left_ability;

	ImagePanel all;
	ImagePanel board;
	JPanel player1_champions;
	JPanel player2_champions;
	JPanel action;
	JPanel directions_move;
	JPanel directions_attack;
	JPanel directions_ability;
	JPanel abilities;

	JLabel player_name_1;
	JLabel player_name_2;
	JLabel leader_1_ability_used;
	JLabel leader_2_ability_used;

	JLabel ability_1;
	JLabel ability_2;
	JLabel ability_3;

	JLabel effects;

	JLabel player1_c1;
	JLabel player1_c2;
	JLabel player1_c3;

	JLabel player2_c1;
	JLabel player2_c2;
	JLabel player2_c3;

	JLabel p1champ1;
	JLabel p1champ2;
	JLabel p1champ3;

	JLabel p2champ1;
	JLabel p2champ2;
	JLabel p2champ3;

	JProgressBar player1_hp_bar_c1;
	JProgressBar player1_hp_bar_c2;
	JProgressBar player1_hp_bar_c3;

	JProgressBar player2_hp_bar_c1;
	JProgressBar player2_hp_bar_c2;
	JProgressBar player2_hp_bar_c3;

	JProgressBar player1_mana_bar_c1;
	JProgressBar player1_mana_bar_c2;
	JProgressBar player1_mana_bar_c3;

	JProgressBar player2_mana_bar_c1;
	JProgressBar player2_mana_bar_c2;
	JProgressBar player2_mana_bar_c3;

	private JLabel next_champ;

	private ArrayList<Champion> champions;

	private ArrayList<JButton> champion_buttons;
	private ArrayList<JButton> cover_buttons;

	private ArrayList<ImageIcon> champions_images;

	private JLabel current_c1;

	private JLabel current_c2;

	private JLabel current_c3;

	private JLabel current_c4;

	private Component current_c5;

	private Component current_c6;

	private JLabel action_points;

	private Champion champion_rand;

	private Cover cover_rand;

	private ArrayList<Cover> covers;

	private ArrayList<Champion> champions_turn;

	public Board(Game game) {

		this.game = game;

		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // 1366,768
		this.setLayout(null);
		this.setUndecorated(true);
		this.setVisible(true);

		player_name_1 = new JLabel("Player_1 Name: " + game.getFirstPlayer().getName());
		player_name_1.setForeground(Color.CYAN);
		

		leader_1_ability_used = new JLabel("Leader Ability Used: " + game.isFirstLeaderAbilityUsed());
		leader_1_ability_used.setForeground(Color.CYAN);
		

		player_name_2 = new JLabel("Player_2 Name: " + game.getSecondPlayer().getName());
		player_name_2.setForeground(Color.CYAN);
		
		
		leader_2_ability_used = new JLabel("Leader Ability Used: " + game.isSecondLeaderAbilityUsed());
		leader_2_ability_used.setForeground(Color.CYAN);
		


		p1c1 = game.getFirstPlayer().getTeam().get(0);
		p1c2 = game.getFirstPlayer().getTeam().get(1);
		p1c3 = game.getFirstPlayer().getTeam().get(2);

		p2c1 = game.getSecondPlayer().getTeam().get(0);
		p2c2 = game.getSecondPlayer().getTeam().get(1);
		p2c3 = game.getSecondPlayer().getTeam().get(2);

		player1_hp_bar_c1 = new JProgressBar(0, (game.getFirstPlayer().getTeam().get(0).getCurrentHP()));
		player1_hp_bar_c1.setString(Integer.toString(game.getFirstPlayer().getTeam().get(0).getCurrentHP()) + "/"
				+ Integer.toString(game.getFirstPlayer().getTeam().get(0).getMaxHP()));
		player1_hp_bar_c1.setStringPainted(true);
		player1_hp_bar_c2 = new JProgressBar(0, (game.getFirstPlayer().getTeam().get(1).getCurrentHP()));
		;
		player1_hp_bar_c2.setString(Integer.toString(game.getFirstPlayer().getTeam().get(1).getCurrentHP()) + "/"
				+ Integer.toString(game.getFirstPlayer().getTeam().get(1).getMaxHP()));
		player1_hp_bar_c2.setStringPainted(true);
		player1_hp_bar_c3 = new JProgressBar(0, (game.getFirstPlayer().getTeam().get(2).getCurrentHP()));
		;
		player1_hp_bar_c3.setString(Integer.toString(game.getFirstPlayer().getTeam().get(2).getCurrentHP()) + "/"
				+ Integer.toString(game.getFirstPlayer().getTeam().get(2).getMaxHP()));
		player1_hp_bar_c3.setStringPainted(true);

		player2_hp_bar_c1 = new JProgressBar(0, (game.getSecondPlayer().getTeam().get(0).getCurrentHP()));
		;
		;
		player2_hp_bar_c1.setString(Integer.toString(game.getSecondPlayer().getTeam().get(0).getCurrentHP()) + "/"
				+ Integer.toString(game.getSecondPlayer().getTeam().get(0).getMaxHP()));
		player2_hp_bar_c1.setStringPainted(true);
		player2_hp_bar_c2 = new JProgressBar(0, (game.getSecondPlayer().getTeam().get(1).getCurrentHP()));
		;
		;
		player2_hp_bar_c2.setString(Integer.toString(game.getSecondPlayer().getTeam().get(1).getCurrentHP()) + "/"
				+ Integer.toString(game.getSecondPlayer().getTeam().get(1).getMaxHP()));
		player2_hp_bar_c2.setStringPainted(true);
		player2_hp_bar_c3 = new JProgressBar(0, (game.getSecondPlayer().getTeam().get(2).getCurrentHP()));
		;
		;
		player2_hp_bar_c3.setString(Integer.toString(game.getSecondPlayer().getTeam().get(2).getCurrentHP()) + "/"
				+ Integer.toString(game.getSecondPlayer().getTeam().get(2).getMaxHP()));
		player2_hp_bar_c3.setStringPainted(true);

		player1_hp_bar_c1.setBounds(82, 250, 200, 30);
		player1_hp_bar_c1.setForeground(Color.red);
		player1_hp_bar_c1.setBackground(Color.black);
		player1_hp_bar_c1.setValue(game.getFirstPlayer().getTeam().get(0).getCurrentHP());
		player1_hp_bar_c2.setBounds(82, 400, 200, 30);
		player1_hp_bar_c2.setForeground(Color.red);
		player1_hp_bar_c2.setBackground(Color.black);
		player1_hp_bar_c2.setValue(game.getFirstPlayer().getTeam().get(1).getCurrentHP());
		player1_hp_bar_c3.setBounds(82, 550, 200, 30);
		player1_hp_bar_c3.setForeground(Color.red);
		player1_hp_bar_c3.setBackground(Color.black);
		player1_hp_bar_c3.setValue(game.getFirstPlayer().getTeam().get(2).getCurrentHP());

		player2_hp_bar_c1.setBounds(this.getWidth() - 82 - 200, 250, 200, 30);
		player2_hp_bar_c1.setForeground(Color.red);
		player2_hp_bar_c1.setBackground(Color.black);
		player2_hp_bar_c1.setValue(game.getSecondPlayer().getTeam().get(0).getCurrentHP());
		player2_hp_bar_c2.setBounds(this.getWidth() - 82 - 200, 400, 200, 30);
		player2_hp_bar_c2.setForeground(Color.red);
		player2_hp_bar_c2.setBackground(Color.black);
		player2_hp_bar_c2.setValue(game.getSecondPlayer().getTeam().get(1).getCurrentHP());
		player2_hp_bar_c3.setBounds(this.getWidth() - 82 - 200, 550, 200, 30);
		player2_hp_bar_c3.setForeground(Color.red);
		player2_hp_bar_c3.setBackground(Color.black);
		player2_hp_bar_c3.setValue(game.getSecondPlayer().getTeam().get(2).getCurrentHP());

		ImageIcon exit_icon = new ImageIcon("exit.png");
		Image exit_image = exit_icon.getImage();
		exit_image = exit_image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		
		exit_icon = new ImageIcon(exit_image);

		exit = new JButton(exit_icon);
		// exit.setBounds(all.getWidth() - 50, 0, 50, 50);

		ImageIcon board_icon = new ImageIcon("board.jpg");
		Image board_image = board_icon.getImage();
		board_image = board_image.getScaledInstance(750, 500, java.awt.Image.SCALE_SMOOTH);

		board = new ImagePanel(board_image);
		board.setLayout(new GridLayout(5, 5));
		board.setBounds(308, 180, 750, 500);

		endturn = new JButton("End Turn");
		endturn.addActionListener((e) -> {

			game.endTurn();

			next_champ.setText("");

			ability_1.setText("");
			ability_2.setText("");
			ability_3.setText("");

			current_c1.setVisible(false);
			current_c2.setVisible(false);
			current_c3.setVisible(false);
			current_c4.setVisible(false);
			current_c5.setVisible(false);
			current_c6.setVisible(false);
			next_champ.setText(""); refreshboard();

			abilities.removeAll();
			abilities.repaint();
			abilities.revalidate();

			ability1.setText(game.getCurrentChampion().getAbilities().get(0).getName());
			ability2.setText(game.getCurrentChampion().getAbilities().get(1).getName());
			ability3.setText(game.getCurrentChampion().getAbilities().get(2).getName());

			abilities.add(ability1);
			abilities.add(ability2);
			abilities.add(ability3);
			abilities.add(cancel);

		});

		cancel = new JButton("Cancel");
		cancel.addActionListener((e) -> {

			if (abilities != null)
				abilities.setVisible(false);

			if (action != null)
				action.setVisible(true);

		});

		cancel1 = new JButton("Cancel");
		cancel1.addActionListener((e) -> {

			if (directions_move != null)
				directions_move.setVisible(false);

			if (action != null)
				action.setVisible(true);

		});

		cancel2 = new JButton("Cancel");
		cancel2.addActionListener((e) -> {

			if (directions_attack != null)
				directions_attack.setVisible(false);

			if (action != null)
				action.setVisible(true);

		});

		cancel3 = new JButton("Cancel");
		cancel3.addActionListener((e) -> {

			if (directions_ability != null)
				directions_ability.setVisible(false);

			if (action != null)
				action.setVisible(true);

		});

		leaderability = new JButton("Leader Ability");
		leaderability.addActionListener((e) -> {

			try {
				game.useLeaderAbility();
			} catch (LeaderNotCurrentException | LeaderAbilityAlreadyUsedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			next_champ.setText("");
			next_champ.setText(""); refreshboard();
			if(game.isFirstLeaderAbilityUsed())
				leader_1_ability_used.setText("Leader Ability Used: " + game.isFirstLeaderAbilityUsed());
			if(game.isSecondLeaderAbilityUsed())
				leader_2_ability_used.setText("Leader Ability Used: " + game.isSecondLeaderAbilityUsed());

		});

		up_move = new JButton("Up");
		up_move.addActionListener((e) -> {
			try {
				game.move(Direction.UP);

			} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			directions_move.setVisible(false);
			action.setVisible(true);
			next_champ.setText(""); next_champ.setText(""); refreshboard();
		});
		down_move = new JButton("Down");
		down_move.addActionListener((e) -> {
			try {
				game.move(Direction.DOWN);

			} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			directions_move.setVisible(false);
			action.setVisible(true);
			next_champ.setText(""); refreshboard();
		});
		right_move = new JButton("Right");
		right_move.addActionListener((e) -> {
			try {
				game.move(Direction.RIGHT);

			} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			directions_move.setVisible(false);
			action.setVisible(true);
			next_champ.setText(""); refreshboard();
		});
		left_move = new JButton("Left");
		left_move.addActionListener((e) -> {
			try {
				game.move(Direction.LEFT);

			} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			directions_move.setVisible(false);
			action.setVisible(true);
			next_champ.setText(""); refreshboard();
		});

		up_attack = new JButton("Up");
		up_attack.addActionListener((e) -> {
			try {
				game.attack(Direction.UP);
			} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			directions_attack.setVisible(false);
			action.setVisible(true);
			next_champ.setText(""); refreshboard();
		});
		down_attack = new JButton("Down");
		down_attack.addActionListener((e) -> {
			try {
				game.attack(Direction.DOWN);
			} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			directions_attack.setVisible(false);
			action.setVisible(true);
			next_champ.setText(""); refreshboard();
		});
		right_attack = new JButton("Right");
		right_attack.addActionListener((e) -> {
			try {
				game.attack(Direction.RIGHT);
			} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			directions_attack.setVisible(false);
			action.setVisible(true);
			next_champ.setText(""); refreshboard();
		});
		left_attack = new JButton("Left");
		left_attack.addActionListener((e) -> {
			try {
				game.attack(Direction.LEFT);
			} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			directions_attack.setVisible(false);
			action.setVisible(true);
			next_champ.setText(""); refreshboard();
		});

		up_ability = new JButton("Up");
		up_ability.addActionListener((e) -> {
			System.out.println("why1");
			if (direction1 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(0), Direction.UP);
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			if (direction2 == true) {
				try {
					System.out.println("why");
					game.castAbility(game.getCurrentChampion().getAbilities().get(1), Direction.UP);
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			if (direction3 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(2), Direction.UP);
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

			direction1 = false;
			direction2 = false;
			direction3 = false;
			next_champ.setText(""); refreshboard();

			directions_ability.setVisible(false);
			action.setVisible(true);
			this.repaint();
			this.revalidate();
			next_champ.setText(""); refreshboard();

		});

		down_ability = new JButton("Down");
		down_ability.addActionListener((e) -> {

			if (direction1 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(0), Direction.DOWN);
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			if (direction2 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(1), Direction.DOWN);
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			if (direction3 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(2), Direction.DOWN);
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

			direction1 = false;
			direction2 = false;
			direction3 = false;
			next_champ.setText(""); refreshboard();

			directions_ability.setVisible(false);
			action.setVisible(true);
			this.repaint();
			this.revalidate();
			next_champ.setText(""); refreshboard();

		});

		right_ability = new JButton("Right");
		right_ability.addActionListener((e) -> {

			if (direction1 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(0), Direction.RIGHT);
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			if (direction2 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(1), Direction.RIGHT);
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			if (direction3 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(2), Direction.RIGHT);
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

			direction1 = false;
			direction2 = false;
			direction3 = false;
			next_champ.setText(""); refreshboard();

			directions_ability.setVisible(false);
			action.setVisible(true);
			this.repaint();
			this.revalidate();
			next_champ.setText(""); refreshboard();

		});

		left_ability = new JButton("Left");
		left_ability.addActionListener((e) -> {

			if (direction1 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(0), Direction.LEFT);
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			if (direction2 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(1), Direction.LEFT);
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			if (direction3 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(2), Direction.LEFT);
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

			direction1 = false;
			direction2 = false;
			direction3 = false;
			next_champ.setText(""); refreshboard();

			directions_ability.setVisible(false);
			action.setVisible(true);
			this.repaint();
			this.revalidate();
			next_champ.setText(""); refreshboard();
		});

		// up_ability = new JButton("Up");
		// down_ability = new JButton("Down");
		// right_ability = new JButton("Right");
		// left_ability = new JButton("Left");

		action = new JPanel();
		action.setLayout(new GridLayout(1, 5));
		action.setBounds(308, 685, 750, 80);

		directions_move = new JPanel();
		directions_move.setLayout(new GridLayout(1, 5));
		directions_move.setBounds(308, 685, 750, 80);
		directions_move.add(up_move);
		directions_move.add(down_move);
		directions_move.add(right_move);
		directions_move.add(left_move);
		directions_move.add(cancel1);
		directions_move.setVisible(false);

		directions_attack = new JPanel();
		directions_attack.setLayout(new GridLayout(1, 5));
		directions_attack.setBounds(308, 685, 750, 80);
		directions_attack.add(up_attack);
		directions_attack.add(down_attack);
		directions_attack.add(right_attack);
		directions_attack.add(left_attack);
		directions_attack.add(cancel2);
		directions_attack.setVisible(false);

		directions_ability = new JPanel();
		directions_ability.setLayout(new GridLayout(1, 5));
		directions_ability.setBounds(308, 685, 750, 80);
		directions_ability.add(up_ability);
		directions_ability.add(down_ability);
		directions_ability.add(right_ability);
		directions_ability.add(left_ability);
		directions_ability.add(cancel3);
		directions_ability.setVisible(false);

		attack = new JButton("Attack");
		attack.addActionListener((e) -> {

			action.setVisible(false);
			directions_attack.setVisible(true);

		});

		move = new JButton("Move");
		move.addActionListener((e) -> {

			action.setVisible(false);
			directions_move.setVisible(true);

		});

		ability1 = new JButton(game.getCurrentChampion().getAbilities().get(0).getName());
		ability1.addActionListener((e) -> {

			if (game.getCurrentChampion().getAbilities().get(0).getCastArea() == AreaOfEffect.DIRECTIONAL) {

				direction1 = true;
				action.setVisible(false);
				abilities.setVisible(false);
				directions_ability.setVisible(true);
				this.repaint();
				this.revalidate();

			} else if (game.getCurrentChampion().getAbilities().get(0).getCastArea() == AreaOfEffect.SINGLETARGET) {

				hit = true;

			} else {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(0));
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				abilities.setVisible(false);
				action.setVisible(true);
				next_champ.setText(""); refreshboard();
			}

		});

		ability2 = new JButton(game.getCurrentChampion().getAbilities().get(1).getName());
		ability2.addActionListener((e) -> {

			if (game.getCurrentChampion().getAbilities().get(1).getCastArea() == AreaOfEffect.DIRECTIONAL) {

				direction2 = true;
				action.setVisible(false);
				abilities.setVisible(false);
				directions_ability.setVisible(true);
				this.repaint();
				this.revalidate();

			} else if (game.getCurrentChampion().getAbilities().get(1).getCastArea() == AreaOfEffect.SINGLETARGET) {

				hit2 = true;

			} else {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(1));
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				abilities.setVisible(false);
				action.setVisible(true);
				next_champ.setText(""); refreshboard();
			}

		});

		ability3 = new JButton(game.getCurrentChampion().getAbilities().get(2).getName());
		ability3.addActionListener((e) -> {

			if (game.getCurrentChampion().getAbilities().get(2).getCastArea() == AreaOfEffect.DIRECTIONAL) {

				direction3 = true;
				action.setVisible(false);
				abilities.setVisible(false);
				directions_ability.setVisible(true);
				this.repaint();
				this.revalidate();

			} else if (game.getCurrentChampion().getAbilities().get(2).getCastArea() == AreaOfEffect.SINGLETARGET) {

				hit3 = true;

			} else {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(2));
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				abilities.setVisible(false);
				action.setVisible(true);
				next_champ.setText(""); refreshboard();
			}

		});

		castability = new JButton("Cast an Ability");
		castability.addActionListener((e) -> {

			action.setVisible(false);
			abilities.setVisible(true);
			this.repaint();
			this.revalidate();

		});

		abilities = new JPanel();
		abilities.setLayout(new GridLayout(1, 4));
		abilities.setBounds(308, 685, 750, 80);
		abilities.add(ability1);
		abilities.add(ability2);
		abilities.add(ability3);
		abilities.add(cancel);
		abilities.setVisible(false);

		ImageIcon background_icon = new ImageIcon("background.jpg");
		Image background = background_icon.getImage();
		background = background.getScaledInstance(this.getWidth(), this.getHeight(), java.awt.Image.SCALE_SMOOTH);

		all = new ImagePanel(background);
		all.setLayout(null);
		all.setBounds(0, 0, this.getWidth(), this.getHeight());

		champions_images = new ArrayList<ImageIcon>();

		champions_images.add(new ImageIcon("Captain America.png"));
		champions_images.add(new ImageIcon("Deadpool.png"));
		champions_images.add(new ImageIcon("Dr.Strange.png"));
		champions_images.add(new ImageIcon("Electro.png"));
		champions_images.add(new ImageIcon("Ghost Rider.png"));
		champions_images.add(new ImageIcon("Hela.png"));
		champions_images.add(new ImageIcon("Hulk.png"));
		champions_images.add(new ImageIcon("Iceman.png"));
		champions_images.add(new ImageIcon("Ironman.png"));
		champions_images.add(new ImageIcon("Loki.png"));
		champions_images.add(new ImageIcon("Quicksilver.png"));
		champions_images.add(new ImageIcon("Spiderman.png"));
		champions_images.add(new ImageIcon("Thor.png"));
		champions_images.add(new ImageIcon("Venom.png"));
		champions_images.add(new ImageIcon("Yellow Jack.png"));

		Game.loadAbilities("Abilities.csv");
		Game.loadChampions("Champions.csv");

		JLabel current_champion = new JLabel("Current Chapion:");
		current_champion.setBounds(10,10,all.getWidth(),20);
		current_champion.setFont(new Font("Serrif", Font.PLAIN, 20));
		current_champion.setForeground(Color.GREEN);
		all.add(current_champion);

		for (int i = 0; i < champions_images.size(); i++) {

			if (game.getFirstPlayer().getTeam().get(0).getName()
					.equals(Game.getAvailableChampions().get(i).getName())) {

				Image champ_image = champions_images.get(i).getImage();
				champ_image = champ_image.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
				ImageIcon champ_fin = new ImageIcon(champ_image);

				current_c1 = new JLabel(champ_fin);
				current_c1.setBounds(10,30,70,70);
				current_c1.setVisible(false);
				all.add(current_c1);

				player1_c1 = new JLabel(champ_fin);
				player1_c1.setBounds(10, 250, 70, 70);
				player1_c1.addMouseListener(this);
				all.add(player1_c1);
				all.repaint();
				all.revalidate();
			}
			if (game.getFirstPlayer().getTeam().get(1).getName()
					.equals(Game.getAvailableChampions().get(i).getName())) {

				Image champ_image = champions_images.get(i).getImage();
				champ_image = champ_image.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
				ImageIcon champ_fin = new ImageIcon(champ_image);

				current_c2 = new JLabel(champ_fin);
				current_c2.setBounds(10,30,70,70);
				current_c2.setVisible(false);
				all.add(current_c2);

				player1_c2 = new JLabel(champ_fin);
				player1_c2.setBounds(10, 400, 70, 70);
				player1_c2.addMouseListener(this);
				all.add(player1_c2);
				all.repaint();
				all.revalidate();
			}
			if (game.getFirstPlayer().getTeam().get(2).getName()
					.equals(Game.getAvailableChampions().get(i).getName())) {

				Image champ_image = champions_images.get(i).getImage();
				champ_image = champ_image.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
				ImageIcon champ_fin = new ImageIcon(champ_image);

				current_c3 = new JLabel(champ_fin);				
				current_c3.setBounds(10,30,70,70);
				current_c3.setVisible(false);
				all.add(current_c3);

				player1_c3 = new JLabel(champ_fin);
				player1_c3.setBounds(10, 550, 70, 70);
				player1_c3.addMouseListener(this);
				all.add(player1_c3);
				all.repaint();
				all.revalidate();
			}

		}

		for (int i = 0; i < champions_images.size(); i++) {

			if (game.getSecondPlayer().getTeam().get(0).getName()
					.equals(Game.getAvailableChampions().get(i).getName())) {

				Image champ_image = champions_images.get(i).getImage();
				champ_image = champ_image.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
				ImageIcon champ_fin = new MirrorImageIcon(champ_image);

				current_c4 = new JLabel(champ_fin);
				current_c4.setBounds(10,30,70,70);
				current_c4.setVisible(false);
				all.add(current_c4);
			
				player2_c1 = new JLabel(champ_fin);
				player2_c1.setBounds(this.getWidth() - 80, 250, 70, 70);
				player2_c1.addMouseListener(this);
				all.add(player2_c1);
				all.repaint();
				all.revalidate();
			}
			if (game.getSecondPlayer().getTeam().get(1).getName()
					.equals(Game.getAvailableChampions().get(i).getName())) {

				Image champ_image = champions_images.get(i).getImage();
				champ_image = champ_image.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
				ImageIcon champ_fin = new MirrorImageIcon(champ_image);

				current_c5 = new JLabel(champ_fin);
				current_c5.setBounds(10,30,70,70);
				current_c5.setVisible(false);
				all.add(current_c5);

				player2_c2 = new JLabel(champ_fin);
				player2_c2.setBounds(this.getWidth() - 80, 400, 70, 70);
				player2_c2.addMouseListener(this);
				all.add(player2_c2);
				all.repaint();
				all.revalidate();
			}
			if (game.getSecondPlayer().getTeam().get(2).getName()
					.equals(Game.getAvailableChampions().get(i).getName())) {

				Image champ_image = champions_images.get(i).getImage();
				champ_image = champ_image.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
				ImageIcon champ_fin = new MirrorImageIcon(champ_image);

				current_c6 = new JLabel(champ_fin);
				current_c6.setBounds(10,30,70,70);
				current_c6.setVisible(false);
				all.add(current_c6);
				
				player2_c3 = new JLabel(champ_fin);
				player2_c3.setBounds(this.getWidth() - 80, 550, 70, 70);
				player2_c3.addMouseListener(this);
				all.add(player2_c3);
				all.repaint();
				all.revalidate();
			}

		}

		exit.setFocusable(false);
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		exit.setBounds(all.getWidth() - 50, 0, 50, 50);
		exit.addActionListener((e) -> System.exit(0));
		all.add(exit);

		action.add(attack);
		action.add(move);
		action.add(castability);
		action.add(leaderability);
		action.add(endturn);

		player_name_1.setBounds(10, 170, all.getWidth(), 30);
		player_name_1.setFont(new Font("Serrif", Font.PLAIN, 17));
		leader_1_ability_used.setBounds(10, 190, all.getWidth(), 30);
		leader_1_ability_used.setFont(new Font("Serrif", Font.PLAIN, 17));
		player_name_2.setBounds(all.getWidth() - 300, 170, all.getWidth(), 30);
		player_name_2.setFont(new Font("Serrif", Font.PLAIN, 17));
		leader_2_ability_used.setBounds(all.getWidth() - 300, 190, all.getWidth(), 30);
		leader_2_ability_used.setFont(new Font("Serrif", Font.PLAIN, 17));

		all.add(player_name_1);
		all.add(player_name_2);
		all.add(leader_1_ability_used);
		all.add(leader_2_ability_used);

		all.add(action);
		all.add(abilities);
		all.add(directions_move);
		all.add(directions_attack);
		all.add(directions_ability);
		all.add(player1_hp_bar_c1);
		all.add(player1_hp_bar_c2);
		all.add(player1_hp_bar_c3);
		all.add(player2_hp_bar_c1);
		all.add(player2_hp_bar_c2);
		all.add(player2_hp_bar_c3);
		this.add(all);
		this.repaint();
		this.revalidate();

		refreshboard();
		// all.add(board);
		this.repaint();
		System.out.println(this.getHeight());

		// this.add(board);

	}

	public void refreshboard() {
		
		//all.remove(next_champ);
		all.remove(board);
		all.repaint();
		all.revalidate();
		board.removeAll();
		this.repaint();
		this.revalidate();

		Game.loadAbilities("Abilities.csv");
		Game.loadChampions("Champions.csv");

		board.repaint();
		board.revalidate();

		// leader_1_ability_used = new JLabel("Leader Ability Used: " + game.isFirstLeaderAbilityUsed());
		// leader_2_ability_used = new JLabel("Leader Ability Used: " + game.isSecondLeaderAbilityUsed());

		champions_turn = new ArrayList<Champion>();

		if(game.getTurnOrder().size() >= 2){
			// System.out.println(((Champion)(game.getTurnOrder().peekMin())).getName());
			Champion temp_champ = (Champion)game.getTurnOrder().remove();
			while(game.getTurnOrder().size() >= 1 && hasEffect((Champion)game.getTurnOrder().peekMin(), "Stun")){
				champions_turn.add((Champion)game.getTurnOrder().remove());
			}
			System.out.println(((Champion)(game.getTurnOrder().peekMin())).getName());
			System.out.println(((Champion)(game.getTurnOrder().peekMin())).getCondition());
			next_champ = new JLabel("Next Champion: " + ((Champion)(game.getTurnOrder().peekMin())).getName());
			game.getTurnOrder().insert(temp_champ);
			next_champ.setForeground(Color.GREEN);
			next_champ.setFont(new Font("Serrif", Font.PLAIN, 20));
			next_champ.setBounds(all.getWidth() - 400, 5, all.getWidth(),30);
			all.add(next_champ);

			for (Champion woah : champions_turn) {
				game.getTurnOrder().insert(woah);
			}
		}
		else{
			Champion next = null;
			for(Champion nextchamp:game.getFirstPlayer().getTeam()){
				if(next == null) next = nextchamp;
				else{
					if(hasEffect(next, "Stun")|| next.getSpeed() < nextchamp.getSpeed() && !hasEffect(nextchamp, "Stun")){ 
						System.out.println("ok");
						next = nextchamp;}
				}
				//System.out.println(next.getName());
			}
			for(Champion nextchamp:game.getSecondPlayer().getTeam()){
				if(next == null) next = nextchamp;
				else{
					if(hasEffect(next, "Stun") || next.getSpeed() < nextchamp.getSpeed() && !hasEffect(nextchamp, "Stun")) next = nextchamp;
				}
				//System.out.println( next.getName());
			}
			//Champion temp_champ = (Champion)game.getTurnOrder().remove();
			next_champ = new JLabel("Next Champion: " + next.getName());
			//game.getTurnOrder().insert(temp_champ);
			next_champ.setForeground(Color.GREEN);
			next_champ.setFont(new Font("Serrif", Font.PLAIN, 20));
			next_champ.setBounds(all.getWidth() - 400, 5, all.getWidth(),30);
			all.add(next_champ);
		}

		ImageIcon board_icon = new ImageIcon("board.jpg");
		Image board_image = board_icon.getImage();
		board_image = board_image.getScaledInstance(750, 500, java.awt.Image.SCALE_SMOOTH);

		board = new ImagePanel(board_image);
		board.setLayout(new GridLayout(5, 5));
		board.setBounds(308, 180, 750, 500);

		cover_buttons = new ArrayList<>();
		covers = new ArrayList<>();

		champions = new ArrayList<>();
		champion_buttons = new ArrayList<>();

		for (int i = 0; i < 5; i++) {

			for (int j = 0; j < 5; j++) {

				if (game.getBoard()[i][j] instanceof Cover) {

					//cover_rand = (Cover)game.getBoard()[i][j];

					JButton cover = new JButton("Hp: " + ((Cover) (game.getBoard()[i][j])).getCurrentHP());
					cover.setContentAreaFilled(false);
					cover.setFocusable(false);
					cover.setIcon(new ImageIcon("cover.png"));
					JLabel temp = new JLabel("Hp: " + ((Cover) (game.getBoard()[i][j])).getCurrentHP());
					temp.setAlignmentX(CENTER_ALIGNMENT);
					temp.setAlignmentY(CENTER_ALIGNMENT);
					cover.add(temp);

					covers.add(((Cover) (game.getBoard()[i][j])));
					cover_buttons.add(cover);
					
					cover.setBounds(50,50,50,50);
					cover.setSize(100,100);

					board.add(cover);

				} else if (game.getBoard()[i][j] instanceof Champion) {

					// champion_buttons = new ArrayList<>();
					JButton champion = new JButton();
					champion.setOpaque(false);
					champion.setContentAreaFilled(false);
					champion.setFocusable(false);

					for (int k = 0; k < Game.getAvailableChampions().size(); k++) {
						//champion_rand = ((Champion) (game.getBoard()[i][j]));
						//System.out.println(i + " " + j);
						if (((Champion) (game.getBoard()[i][j])).getName()
								.equals(Game.getAvailableChampions().get(k).getName())) {
							
							champions.add((Champion) (game.getBoard()[i][j]));
							
							Image temp = champions_images.get(k).getImage();
							temp = temp.getScaledInstance(100, 70, java.awt.Image.SCALE_SMOOTH);
							ImageIcon final_image = new ImageIcon(temp);
							champion.setIcon(final_image);
							if (game.getFirstPlayer().getTeam().contains((Champion) (game.getBoard()[i][j]))) {

								champion.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));

							} else {
								champion.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
							}
							//System.out.println("ok");
							//System.out.println(champion_buttons.size());

							champion_buttons.add(champion);

						}
					}

					// champion.setIcon(champions_images.get(Game.getAvailableChampions().indexOf((Champion)(game.getBoard()[i][j]))));

					board.add(champion);

				} else {

					JButton cover = new JButton();
					cover.setContentAreaFilled(false);
					cover.setForeground(Color.white);

					board.add(cover);

				}

			}

		}
		
		if(champions.size() >= 1){
		champion_buttons.get(0).addActionListener((e) -> {
			//System.out.println(8);
			if (hit == true) {
				//System.out.println(8);
				// System.out.println((int)(champion_rand).getLocation().getX());
				// System.out.println((int)(champion_rand).getLocation().getY());
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(0),
							(int) (champions.get(0)).getLocation().getX(),
							(int) (champions.get(0)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (abilities != null){
					System.out.println("whyyyy");
					abilities.setVisible(false);
				}
				if (action != null)
					action.setVisible(true);
				hit = false;
				next_champ.setText(""); refreshboard();
			}

			if (hit2 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(1),
							(int) (champions.get(0)).getLocation().getX(),
							(int) (champions.get(0)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (abilities != null)
					abilities.setVisible(false);

				if (action != null)
					action.setVisible(true);
				hit2 = false;
				next_champ.setText(""); refreshboard();
			}

			if (hit3 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(2),
							(int) (champions.get(0)).getLocation().getX(),
							(int) (champions.get(0)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (abilities != null)
					abilities.setVisible(false);

				if (action != null)
					action.setVisible(true);
				hit3 = false;
				next_champ.setText(""); refreshboard();
			}
		});
	}
		if(champions.size() >= 2){
		champion_buttons.get(1).addActionListener((e) -> {
			//System.out.println(8);
			if (hit == true) {
				//System.out.println(8);
				// System.out.println((int)(champion_rand).getLocation().getX());
				// System.out.println((int)(champion_rand).getLocation().getY());
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(0),
							(int) (champions.get(1)).getLocation().getX(),
							(int) (champions.get(1)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (abilities != null)
					abilities.setVisible(false);

				if (action != null)
					action.setVisible(true);
				hit = false;
				next_champ.setText(""); refreshboard();
			}

			if (hit2 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(1),
							(int) (champions.get(1)).getLocation().getX(),
							(int) (champions.get(1)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (abilities != null)
					abilities.setVisible(false);

				if (action != null)
					action.setVisible(true);
				hit2 = false;
				next_champ.setText(""); refreshboard();
			}

			if (hit3 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(2),
							(int) (champions.get(1)).getLocation().getX(),
							(int) (champions.get(1)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (abilities != null)
					abilities.setVisible(false);

				if (action != null)
					action.setVisible(true);
				hit3 = false;
				next_champ.setText(""); refreshboard();
			}
		});
	}

	if(champions.size() >= 3){
		champion_buttons.get(2).addActionListener((e) -> {
			//System.out.println(8);
			if (hit == true) {
				//System.out.println(8);
				// System.out.println((int)(champion_rand).getLocation().getX());
				// System.out.println((int)(champion_rand).getLocation().getY());
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(0),
							(int) (champions.get(2)).getLocation().getX(),
							(int) (champions.get(2)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (abilities != null){
					abilities.setVisible(false);
				}
				if (action != null)
					action.setVisible(true);
				hit = false;
				next_champ.setText(""); refreshboard();
			}

			if (hit2 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(1),
							(int) (champions.get(2)).getLocation().getX(),
							(int) (champions.get(2)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (abilities != null){
					abilities.setVisible(false);
				}
				if (action != null)
					action.setVisible(true);
				hit2 = false;
				next_champ.setText(""); refreshboard();
			}

			if (hit3 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(2),
							(int) (champions.get(2)).getLocation().getX(),
							(int) (champions.get(2)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (abilities != null){
					abilities.setVisible(false);
				}
				if (action != null)
					action.setVisible(true);
				hit3 = false;
				next_champ.setText(""); refreshboard();
			}
		});
	}
	if(champions.size() >= 4){
		champion_buttons.get(3).addActionListener((e) -> {
			//System.out.println(8);
			if (hit == true) {
				//System.out.println(8);
				// System.out.println((int)(champion_rand).getLocation().getX());
				// System.out.println((int)(champion_rand).getLocation().getY());
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(0),
							(int) (champions.get(3)).getLocation().getX(),
							(int) (champions.get(3)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (abilities != null){
					abilities.setVisible(false);
				}
				if (action != null)
					action.setVisible(true);
				hit = false;
				next_champ.setText(""); refreshboard();
			}

			if (hit2 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(1),
							(int) (champions.get(3)).getLocation().getX(),
							(int) (champions.get(3)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (abilities != null){
					abilities.setVisible(false);
				}
				if (action != null)
					action.setVisible(true);
				hit2 = false;
				next_champ.setText(""); refreshboard();
			}

			if (hit3 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(2),
							(int) (champions.get(3)).getLocation().getX(),
							(int) (champions.get(3)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (abilities != null){
					abilities.setVisible(false);
				}
				if (action != null)
					action.setVisible(true);
				next_champ.setText(""); refreshboard();
				hit3 = false;
			}
		});
	}
	if(champions.size() >= 5){
		champion_buttons.get(4).addActionListener((e) -> {
			//System.out.println(8);
			if (hit == true) {
				//System.out.println(8);
				// System.out.println((int)(champion_rand).getLocation().getX());
				// System.out.println((int)(champion_rand).getLocation().getY());
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(0),
							(int) (champions.get(4)).getLocation().getX(),
							(int) (champions.get(4)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (abilities != null){
					abilities.setVisible(false);
				}
				if (action != null)
					action.setVisible(true);
				//next_champ.setText(""); refreshboard();
				hit = false;
				next_champ.setText(""); refreshboard();
			}

			if (hit2 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(1),
							(int) (champions.get(4)).getLocation().getX(),
							(int) (champions.get(4)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (abilities != null){
					abilities.setVisible(false);
				}
				if (action != null)
					action.setVisible(true);
				next_champ.setText(""); refreshboard();
				hit2 = false;
			}

			if (hit3 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(2),
							(int) (champions.get(4)).getLocation().getX(),
							(int) (champions.get(4)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (abilities != null){
					abilities.setVisible(false);
				}
				if (action != null)
					action.setVisible(true);
				next_champ.setText(""); refreshboard();
				hit3 = false;
			}
		});
	}
	if(champions.size() >= 6){
		champion_buttons.get(5).addActionListener((e) -> {
			//System.out.println(8);
			if (hit == true) {
				//System.out.println(8);
				// System.out.println((int)(champion_rand).getLocation().getX());
				// System.out.println((int)(champion_rand).getLocation().getY());
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(0),
							(int) (champions.get(5)).getLocation().getX(),
							(int) (champions.get(5)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (abilities != null){
					abilities.setVisible(false);
				}
				if (action != null)
					action.setVisible(true);
				//next_champ.setText(""); refreshboard();
				hit = false;
				next_champ.setText(""); refreshboard();
			}

			if (hit2 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(1),
							(int) (champions.get(5)).getLocation().getX(),
							(int) (champions.get(5)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (abilities != null){
					abilities.setVisible(false);
				}
				if (action != null)
					action.setVisible(true);
				next_champ.setText(""); refreshboard();
				hit2 = false;
			}

			if (hit3 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(2),
							(int) (champions.get(5)).getLocation().getX(),
							(int) (champions.get(5)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (abilities != null){
					abilities.setVisible(false);
				}
				if (action != null)
					action.setVisible(true);
				next_champ.setText(""); refreshboard();
				hit3 = false;
			}
		});
	}
	
	if(covers.size() >= 1){
		cover_buttons.get(0).addActionListener((e) -> {
		if (hit == true) {
			//System.out.println("ok");
			try {
				game.castAbility(game.getCurrentChampion().getAbilities().get(0), (int)(covers.get(0)).getLocation().getX(), (int)(covers.get(0)).getLocation().getY());
			} catch (NotEnoughResourcesException | AbilityUseException
					| CloneNotSupportedException | InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			if (abilities != null){
				abilities.setVisible(false);
			}
			if (action != null)
				action.setVisible(true);
			//next_champ.setText(""); refreshboard();
			hit = false;
			next_champ.setText(""); refreshboard();
		}

		if (hit2 == true) {
			try {
				game.castAbility(game.getCurrentChampion().getAbilities().get(1), (int)(covers.get(0)).getLocation().getX(), (int)(covers.get(0)).getLocation().getY());
			} catch (NotEnoughResourcesException | AbilityUseException
					| CloneNotSupportedException | InvalidTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			if (abilities != null){
				abilities.setVisible(false);
			}
			if (action != null)
				action.setVisible(true);
			//next_champ.setText(""); refreshboard();
			hit2 = false;
			next_champ.setText(""); refreshboard();
		}

		if (hit3 == true) {
			try {
				game.castAbility(game.getCurrentChampion().getAbilities().get(2), (int)(covers.get(0)).getLocation().getX(), (int)(covers.get(0)).getLocation().getY());
			} catch (NotEnoughResourcesException | AbilityUseException
					| CloneNotSupportedException | InvalidTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			if (abilities != null){
				abilities.setVisible(false);
			}
			if (action != null)
				action.setVisible(true);
			//next_champ.setText(""); refreshboard();
			hit3 = false;
			next_champ.setText(""); refreshboard();
		}
	});
	}

	if(covers.size() >= 2){
		cover_buttons.get(1).addActionListener((e) -> {
		if (hit == true) {
			//System.out.println("ok");
			try {
				game.castAbility(game.getCurrentChampion().getAbilities().get(0), (int)(covers.get(1)).getLocation().getX(), (int)(covers.get(1)).getLocation().getY());
			} catch (NotEnoughResourcesException | AbilityUseException
					| CloneNotSupportedException | InvalidTargetException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
			if (abilities != null){
				abilities.setVisible(false);
			}
			if (action != null)
				action.setVisible(true);
			//next_champ.setText(""); refreshboard();
			hit = false;
			next_champ.setText(""); refreshboard();
		}

		if (hit2 == true) {
			try {
				game.castAbility(game.getCurrentChampion().getAbilities().get(1), (int)(covers.get(1)).getLocation().getX(), (int)(covers.get(1)).getLocation().getY());
			} catch (NotEnoughResourcesException | AbilityUseException
					| CloneNotSupportedException | InvalidTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			if (abilities != null){
				abilities.setVisible(false);
			}
			if (action != null)
				action.setVisible(true);
			hit2 = false;
			next_champ.setText(""); refreshboard();
		}

		if (hit3 == true) {
			try {
				game.castAbility(game.getCurrentChampion().getAbilities().get(2), (int)(covers.get(1)).getLocation().getX(), (int)(covers.get(1)).getLocation().getY());
			} catch (NotEnoughResourcesException | AbilityUseException
					| CloneNotSupportedException | InvalidTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			if (abilities != null){
				abilities.setVisible(false);
			}
			if (action != null)
				action.setVisible(true);
			hit3 = false;
			next_champ.setText(""); refreshboard();
		}
	});
	}

	if (covers.size() >= 3) {
		cover_buttons.get(2).addActionListener((e) -> {
			if (hit == true) {
				// System.out.println("ok");
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(0),
							(int) (covers.get(2)).getLocation().getX(), (int) (covers.get(2)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				if (abilities != null){
					abilities.setVisible(false);
				}
				if (action != null)
					action.setVisible(true);
				hit = false;
				next_champ.setText(""); refreshboard();
			}

			if (hit2 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(1),
							(int) (covers.get(2)).getLocation().getX(), (int) (covers.get(2)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (abilities != null){
					abilities.setVisible(false);
				}
				if (action != null)
					action.setVisible(true);
				hit2 = false;
				next_champ.setText(""); refreshboard();
			}

			if (hit3 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(2),
							(int) (covers.get(2)).getLocation().getX(), (int) (covers.get(2)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (abilities != null){
					abilities.setVisible(false);
				}
				if (action != null)
					action.setVisible(true);
				hit3 = false;
				next_champ.setText(""); refreshboard();
			}
		});
	}
	
	if (covers.size() >= 4) {
		cover_buttons.get(3).addActionListener((e) -> {
			if (hit == true) {
				// System.out.println("ok");
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(0),
							(int) (covers.get(3)).getLocation().getX(), (int) (covers.get(3)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				if (abilities != null){
					abilities.setVisible(false);
				}
				if (action != null)
					action.setVisible(true);
				hit = false;
				next_champ.setText(""); refreshboard();
			}

			if (hit2 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(1),
							(int) (covers.get(3)).getLocation().getX(), (int) (covers.get(3)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (abilities != null){
					abilities.setVisible(false);
				}
				if (action != null)
					action.setVisible(true);
				hit2 = false;
				next_champ.setText(""); refreshboard();
			}

			if (hit3 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(2),
							(int) (covers.get(3)).getLocation().getX(), (int) (covers.get(3)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (abilities != null){
					abilities.setVisible(false);
				}
				if (action != null)
					action.setVisible(true);
				hit3 = false;
				next_champ.setText(""); refreshboard();
			}
		});
	}

	if (covers.size() >= 5) {
		cover_buttons.get(4).addActionListener((e) -> {
			if (hit == true) {
				// System.out.println("ok");
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(0),
							(int) (covers.get(4)).getLocation().getX(), (int) (covers.get(4)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				if (abilities != null){
					abilities.setVisible(false);
				}
				if (action != null)
					action.setVisible(true);
				hit = false;
				next_champ.setText(""); refreshboard();
			}

			if (hit2 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(1),
							(int) (covers.get(4)).getLocation().getX(), (int) (covers.get(4)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (abilities != null){
					abilities.setVisible(false);
				}
				if (action != null)
					action.setVisible(true);
				hit2 = false;
				next_champ.setText(""); refreshboard();
			}

			if (hit3 == true) {
				try {
					game.castAbility(game.getCurrentChampion().getAbilities().get(2),
							(int) (covers.get(4)).getLocation().getX(), (int) (covers.get(4)).getLocation().getY());
				} catch (NotEnoughResourcesException | AbilityUseException
						| CloneNotSupportedException | InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (abilities != null){
					abilities.setVisible(false);
				}
				if (action != null)
					action.setVisible(true);
				hit3 = false;
				next_champ.setText(""); refreshboard();
			}
		});
	}

		
		all.add(board);
		//all.add(ability_1);
		all.repaint();
		all.revalidate();

		if(game.getFirstPlayer().getTeam().get(0).getName().equals(game.getCurrentChampion().getName())){
			current_c1.setVisible(true);
		} 
		if(game.getFirstPlayer().getTeam().get(1).getName().equals(game.getCurrentChampion().getName())){
			current_c2.setVisible(true);
		}
		if(game.getFirstPlayer().getTeam().get(2).getName().equals(game.getCurrentChampion().getName())){
			current_c3.setVisible(true);
		} 

		if(game.getSecondPlayer().getTeam().get(0).getName().equals(game.getCurrentChampion().getName())){
			current_c4.setVisible(true);
		}
		if(game.getSecondPlayer().getTeam().get(1).getName().equals(game.getCurrentChampion().getName())){
			current_c5.setVisible(true);
		} 
		if(game.getSecondPlayer().getTeam().get(2).getName().equals(game.getCurrentChampion().getName())){
			current_c6.setVisible(true);
		}
		
		// all.add(board);
		// //all.add(ability_1);
		// all.repaint();
		// all.revalidate();
		// p1c1 = game.getFirstPlayer().getTeam().get(0);
		// p1c2 = game.getFirstPlayer().getTeam().get(1);
		// p1c3 = game.getFirstPlayer().getTeam().get(2);

		// p2c1 = game.getFirstPlayer().getTeam().get(0);
		// p2c2 = game.getFirstPlayer().getTeam().get(1);
		// p2c3 = game.getFirstPlayer().getTeam().get(2);

		player1_hp_bar_c1.setString(Integer.toString(p1c1.getCurrentHP()) + "/" + Integer.toString(p1c1.getMaxHP()));
		player1_hp_bar_c2.setString(Integer.toString(p1c2.getCurrentHP()) + "/" + Integer.toString(p1c2.getMaxHP()));
		player1_hp_bar_c3.setString(Integer.toString(p1c3.getCurrentHP()) + "/" + Integer.toString(p1c3.getMaxHP()));
		player1_hp_bar_c1.setValue(p1c1.getCurrentHP());
		player1_hp_bar_c2.setValue(p1c2.getCurrentHP());
		player1_hp_bar_c3.setValue(p1c3.getCurrentHP());

		player2_hp_bar_c1.setString(Integer.toString(p2c1.getCurrentHP()) + "/" + Integer.toString(p2c1.getMaxHP()));
		player2_hp_bar_c1.setValue(p2c1.getCurrentHP());
		player2_hp_bar_c2.setString(Integer.toString(p2c2.getCurrentHP()) + "/" + Integer.toString(p2c2.getMaxHP()));
		player2_hp_bar_c2.setValue(p2c2.getCurrentHP());
		player2_hp_bar_c3.setString(Integer.toString(p2c3.getCurrentHP()) + "/" + Integer.toString(p2c3.getMaxHP()));
		player2_hp_bar_c3.setValue(p2c3.getCurrentHP());

		if(game.checkGameOver() != null){
			System.out.println(game.checkGameOver().getName());
			JLabel winner = new JLabel(game.checkGameOver().getName() + " IS THE WINNER", SwingConstants.CENTER);
			winner.setBounds(308, 180, 750, 500);
			winner.setForeground(Color.GREEN);
			winner.setFont(new Font("Serrif", Font.PLAIN, 40));
			// winner.setVerticalTextPosition(20);
			// winner.setHorizontalTextPosition(20);
			all.remove(board);
			all.remove(action);
			all.add(winner);
			all.repaint();
			all.revalidate();
			return;
		}

			
		if(ability_1 != null) ability_1.setText("");
		ability_1 = new JLabel("1. Ability Name: " + game.getCurrentChampion().getAbilities().get(0).getName()
			+ " --- " + "Type: ");
	
		if(game.getCurrentChampion().getAbilities().get(0) instanceof DamagingAbility) ability_1.setText(ability_1.getText() + "Damaging Ability --- ");
		else if(game.getCurrentChampion().getAbilities().get(0) instanceof HealingAbility) ability_1.setText(ability_1.getText() + "Healing Ability --- ");
		else if(game.getCurrentChampion().getAbilities().get(0) instanceof CrowdControlAbility) ability_1.setText(ability_1.getText() + "Crowd Control Ability --- ");
			
		ability_1.setText(ability_1.getText() + "AOF: " + game.getCurrentChampion().getAbilities().get(0).getCastArea()
			+ " --- Cast Range: " + game.getCurrentChampion().getAbilities().get(0).getCastRange() 
			+ " --- Mana Cost: " + game.getCurrentChampion().getAbilities().get(0).getManaCost()
			+ " --- Action Cost: " + Integer.toString(game.getCurrentChampion().getAbilities().get(0).getRequiredActionPoints())
			+ " --- Base Cooldown: " + Integer.toString(game.getCurrentChampion().getAbilities().get(0).getBaseCooldown())
			+ " --- Current Cooldown: " + Integer.toString(game.getCurrentChampion().getAbilities().get(0).getCurrentCooldown()));
	
		if(game.getCurrentChampion().getAbilities().get(0) instanceof DamagingAbility) ability_1.setText(ability_1.getText() + " --- Damaging Amount: "
			+ ((DamagingAbility)(game.getCurrentChampion().getAbilities().get(0))).getDamageAmount());
		else if(game.getCurrentChampion().getAbilities().get(0) instanceof HealingAbility) ability_1.setText(ability_1.getText() + " --- Healing Amount: "
			+ ((HealingAbility)(game.getCurrentChampion().getAbilities().get(0))).getHealAmount());
		else if(game.getCurrentChampion().getAbilities().get(0) instanceof CrowdControlAbility) ability_1.setText(ability_1.getText() + " --- CC Effect: "
			+ ((CrowdControlAbility)(game.getCurrentChampion().getAbilities().get(0))).getEffect().getName());
	
		ability_1.setBounds(100,35,all.getWidth(),20);
		ability_1.setForeground(Color.CYAN);
	
		all.add(ability_1);
		all.repaint();
		all.revalidate();

			
		if(ability_2 != null) ability_2.setText("");
		ability_2 = new JLabel("2. Ability Name: " + game.getCurrentChampion().getAbilities().get(1).getName()
			+ " --- " + "Type: ");
	
		if(game.getCurrentChampion().getAbilities().get(1) instanceof DamagingAbility) ability_2.setText(ability_2.getText() + "Damaging Ability --- ");
		else if(game.getCurrentChampion().getAbilities().get(1) instanceof HealingAbility) ability_2.setText(ability_2.getText() + "Healing Ability --- ");
		else if(game.getCurrentChampion().getAbilities().get(1) instanceof CrowdControlAbility) ability_2.setText(ability_2.getText() + "Crowd Control Ability --- ");
		
		ability_2.setText(ability_2.getText() + "AOF: " + game.getCurrentChampion().getAbilities().get(1).getCastArea()
			+ " --- Cast Range: " + game.getCurrentChampion().getAbilities().get(1).getCastRange() 
			+ " --- Mana Cost: " + game.getCurrentChampion().getAbilities().get(1).getManaCost()
			+ " --- Action Cost: " + Integer.toString(game.getCurrentChampion().getAbilities().get(1).getRequiredActionPoints())
			+ " --- Base Cooldown: " + Integer.toString(game.getCurrentChampion().getAbilities().get(1).getBaseCooldown())
			+ " --- Current Cooldown: " + Integer.toString(game.getCurrentChampion().getAbilities().get(1).getCurrentCooldown()));
	
		if(game.getCurrentChampion().getAbilities().get(1) instanceof DamagingAbility) ability_2.setText(ability_2.getText() + " --- Damaging Amount: "
			+ ((DamagingAbility)(game.getCurrentChampion().getAbilities().get(1))).getDamageAmount());
		else if(game.getCurrentChampion().getAbilities().get(1) instanceof HealingAbility) ability_2.setText(ability_2.getText() + " --- Healing Amount: "
			+ ((HealingAbility)(game.getCurrentChampion().getAbilities().get(1))).getHealAmount());
		else if(game.getCurrentChampion().getAbilities().get(1) instanceof CrowdControlAbility) ability_2.setText(ability_2.getText() + " --- CC Effect: "
			+ ((CrowdControlAbility)(game.getCurrentChampion().getAbilities().get(1))).getEffect().getName());
	
		ability_2.setBounds(100,57,all.getWidth(),20);
		ability_2.setForeground(Color.CYAN);
	
		all.add(ability_2);
		all.repaint();
		all.revalidate();
		
		if(ability_3 != null) ability_3.setText("");
		ability_3 = new JLabel("3. Ability Name: " + game.getCurrentChampion().getAbilities().get(2).getName()
			+ " --- " + "Type: ");
	
		if(game.getCurrentChampion().getAbilities().get(2) instanceof DamagingAbility) ability_3.setText(ability_3.getText() + "Damaging Ability --- ");
		else if(game.getCurrentChampion().getAbilities().get(2) instanceof HealingAbility) ability_3.setText(ability_3.getText() + "Healing Ability --- ");
		else if(game.getCurrentChampion().getAbilities().get(2) instanceof CrowdControlAbility) ability_3.setText(ability_3.getText() + "Crowd Control Ability --- ");
			
		ability_3.setText(ability_3.getText() + "AOF: " + game.getCurrentChampion().getAbilities().get(2).getCastArea()
			+ " --- Cast Range: " + game.getCurrentChampion().getAbilities().get(2).getCastRange() 
			+ " --- Mana Cost: " + game.getCurrentChampion().getAbilities().get(2).getManaCost()
			+ " --- Action Cost: " + Integer.toString(game.getCurrentChampion().getAbilities().get(2).getRequiredActionPoints())
			+ " --- Base Cooldown: " + Integer.toString(game.getCurrentChampion().getAbilities().get(2).getBaseCooldown())
			+ " --- Current Cooldown: " + Integer.toString(game.getCurrentChampion().getAbilities().get(2).getCurrentCooldown()));
	
		if(game.getCurrentChampion().getAbilities().get(2) instanceof DamagingAbility) ability_3.setText(ability_3.getText() + " --- Damaging Amount: "
			+ ((DamagingAbility)(game.getCurrentChampion().getAbilities().get(2))).getDamageAmount());
		else if(game.getCurrentChampion().getAbilities().get(2) instanceof HealingAbility) ability_3.setText(ability_3.getText() + " --- Healing Amount: "
			+ ((HealingAbility)(game.getCurrentChampion().getAbilities().get(2))).getHealAmount());
		else if(game.getCurrentChampion().getAbilities().get(2) instanceof CrowdControlAbility) ability_3.setText(ability_3.getText() + " --- CC Effect: "
			+ ((CrowdControlAbility)(game.getCurrentChampion().getAbilities().get(2))).getEffect().getName());
	
		ability_3.setBounds(100,79,all.getWidth(),20);
		ability_3.setForeground(Color.CYAN);
	
		all.add(ability_3);
		all.repaint();
		all.revalidate();

		if(action_points != null) action_points.setText("");
		action_points = new JLabel("Action Points: " + Integer.toString(game.getCurrentChampion().getCurrentActionPoints()));
		action_points.setBounds(100, 96, all.getWidth(), 20);
		action_points.setForeground(Color.CYAN);

		all.add(action_points);
		all.repaint();
		all.revalidate();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == player1_c3) System.out.println("works");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == player1_c2) {
			// p1champ2.setEnabled(true);
			p1champ2 = new JLabel("Name: " + game.getFirstPlayer().getTeam().get(1).getName() + " --- " + "Hp: "
					+ Integer.toString(game.getFirstPlayer().getTeam().get(1).getCurrentHP()) + " --- " + "Mana: "
					+ Integer.toString(game.getFirstPlayer().getTeam().get(1).getMana()) + " --- " + "Action Points: "
					+ Integer.toString(game.getFirstPlayer().getTeam().get(1).getCurrentActionPoints()) + " --- "
					+ "Attack Damage: " + Integer.toString(game.getFirstPlayer().getTeam().get(1).getAttackDamage())
					+ " --- " + "Attack Range: "
					+ Integer.toString(game.getFirstPlayer().getTeam().get(1).getAttackRange())
					+ " --- " + "Speed: " + Integer.toString(game.getFirstPlayer().getTeam().get(1).getSpeed()));

			if(game.getFirstPlayer().getTeam().get(1) instanceof Hero)
				p1champ2.setText(p1champ2.getText() + " --- " + "Type: " + "Hero");
			else if(game.getFirstPlayer().getTeam().get(1) instanceof Villain)
				p1champ2.setText(p1champ2.getText() + " --- " + "Type: " + "Villain");
			else 
				p1champ2.setText(p1champ2.getText() + " --- " + "Type: " + "Anti-Hero");

			if(game.getFirstPlayer().getTeam().get(1).getName().equals(game.getFirstPlayer().getLeader().getName()))
				p1champ2.setText(p1champ2.getText() + " --- " + "Leader Status: Yes");
			else
				p1champ2.setText(p1champ2.getText() + " --- " + "Leader Status: No");

			effects = new JLabel("Applied Effects: ");
			for(Effect effect : game.getFirstPlayer().getTeam().get(1).getAppliedEffects()){
				effects.setText(effects.getText() + effect.getName() + "(" + Integer.toString(effect.getDuration()) + ") --- ");
			}
			
			effects.setForeground(Color.CYAN);
			effects.setFont(new Font("Serif", Font.PLAIN, 17));
			effects.setBounds(0, 0, all.getWidth(), 300);

			p1champ2.setForeground(Color.CYAN);
			p1champ2.setFont(new Font("Serif", Font.PLAIN, 17));
			p1champ2.setBounds(0, 0, all.getWidth(), 250);

			all.add(p1champ2);
			all.add(effects);
			all.repaint();
			all.revalidate();
		}
		if (e.getSource() == player1_c1) {
			// System.out.println("confirm");
			// p1champ1.setEnabled(true);
			p1champ1 = new JLabel("Name: " + game.getFirstPlayer().getTeam().get(0).getName() + " --- " + "Hp: "
					+ Integer.toString(game.getFirstPlayer().getTeam().get(0).getCurrentHP()) + " --- " + "Mana: "
					+ Integer.toString(game.getFirstPlayer().getTeam().get(0).getMana()) + " --- " + "Action Points: "
					+ Integer.toString(game.getFirstPlayer().getTeam().get(0).getCurrentActionPoints()) + " --- "
					+ "Attack Damage: " + Integer.toString(game.getFirstPlayer().getTeam().get(0).getAttackDamage())
					+ " --- " + "Attack Range: "
					+ Integer.toString(game.getFirstPlayer().getTeam().get(0).getAttackRange())
					+ " --- " + "Speed: " + Integer.toString(game.getFirstPlayer().getTeam().get(0).getSpeed()));
					
				if(game.getFirstPlayer().getTeam().get(0) instanceof Hero)
					p1champ1.setText(p1champ1.getText() + " --- " + "Type: " + "Hero");
				else if(game.getFirstPlayer().getTeam().get(0) instanceof Villain)
					p1champ1.setText(p1champ1.getText() + " --- " + "Type: " + "Villain");
				else 
					p1champ1.setText(p1champ1.getText() + " --- " + "Type: " + "Anti-Hero");

				if(game.getFirstPlayer().getTeam().get(0).getName().equals(game.getFirstPlayer().getLeader().getName()))
					p1champ1.setText(p1champ1.getText() + " --- " + "Leader Status: Yes");
				else
					p1champ1.setText(p1champ1.getText() + " --- " + "Leader Status: No");

			effects = new JLabel("Applied Effects: ");
			for(Effect effect : game.getFirstPlayer().getTeam().get(0).getAppliedEffects()){
						effects.setText(effects.getText() + effect.getName() + "(" + Integer.toString(effect.getDuration()) + ") --- ");
					}
					
			effects.setForeground(Color.CYAN);
			effects.setFont(new Font("Serif", Font.PLAIN, 17));
			effects.setBounds(0, 0, all.getWidth(), 305);

			p1champ1.setForeground(Color.CYAN);
			p1champ1.setFont(new Font("Serif", Font.PLAIN, 17));
			p1champ1.setBounds(0, 0, all.getWidth(), 255);

			all.add(effects);
			all.add(p1champ1);
			all.repaint();
			all.revalidate();
		}
		
		if (e.getSource() == player1_c3) {
			// p1champ3.setEnabled(true);
			System.out.println("confirm");
			p1champ3 = new JLabel("Name: " + game.getFirstPlayer().getTeam().get(2).getName() + " --- " + "Hp: "
					+ Integer.toString(game.getFirstPlayer().getTeam().get(2).getCurrentHP()) + " --- " + "Mana: "
					+ Integer.toString(game.getFirstPlayer().getTeam().get(2).getMana()) + " --- " + "Action Points: "
					+ Integer.toString(game.getFirstPlayer().getTeam().get(2).getCurrentActionPoints()) + " --- "
					+ "Attack Damage: " + Integer.toString(game.getFirstPlayer().getTeam().get(2).getAttackDamage())
					+ " --- " + "Attack Range: "
					+ Integer.toString(game.getFirstPlayer().getTeam().get(2).getAttackRange())
					+ " --- " + "Speed: " + Integer.toString(game.getFirstPlayer().getTeam().get(2).getSpeed()));

				if(game.getFirstPlayer().getTeam().get(2) instanceof Hero)
					p1champ3.setText(p1champ3.getText() + " --- " + "Type: " + "Hero");
				else if(game.getFirstPlayer().getTeam().get(2) instanceof Villain)
					p1champ3.setText(p1champ3.getText() + " --- " + "Type: " + "Villain");
				else 
					p1champ3.setText(p1champ3.getText() + " --- " + "Type: " + "Anti-Hero");

				if(game.getFirstPlayer().getTeam().get(2).getName().equals(game.getFirstPlayer().getLeader().getName()))
					p1champ3.setText(p1champ3.getText() + " --- " + "Leader Status: Yes");
				else
					p1champ3.setText(p1champ3.getText() + " --- " + "Leader Status: No");

			effects = new JLabel("Applied Effects: ");
			for(Effect effect : game.getFirstPlayer().getTeam().get(2).getAppliedEffects()){
				effects.setText(effects.getText() + effect.getName() + "(" + Integer.toString(effect.getDuration()) + ") --- ");
				}
					
			effects.setForeground(Color.CYAN);
			effects.setFont(new Font("Serif", Font.PLAIN, 17));
			effects.setBounds(0, 0, all.getWidth(), 305);

			p1champ3.setForeground(Color.CYAN);
			p1champ3.setFont(new Font("Serif", Font.PLAIN, 17));		
			p1champ3.setBounds(0, 0, all.getWidth(), 255);

			all.add(effects);
			all.add(p1champ3);
			all.repaint();
			all.revalidate();
		}
		if (e.getSource() == player2_c1) {
			//System.out.println("confirm");
			// p2champ1.setEnabled(true);
			p2champ1 = new JLabel("Name: " + game.getSecondPlayer().getTeam().get(0).getName() + " --- " + "Hp: "
					+ Integer.toString(game.getSecondPlayer().getTeam().get(0).getCurrentHP()) + " --- " + "Mana: "
					+ Integer.toString(game.getSecondPlayer().getTeam().get(0).getMana()) + " --- " + "Action Points: "
					+ Integer.toString(game.getSecondPlayer().getTeam().get(0).getCurrentActionPoints()) + " --- "
					+ "Attack Damage: " + Integer.toString(game.getSecondPlayer().getTeam().get(0).getAttackDamage())
					+ " --- " + "Attack Range: "
					+ Integer.toString(game.getSecondPlayer().getTeam().get(0).getAttackRange())
					+ " --- " + "Speed: " + Integer.toString(game.getSecondPlayer().getTeam().get(0).getSpeed()));

				if(game.getSecondPlayer().getTeam().get(0) instanceof Hero)
					p2champ1.setText(p2champ1.getText() + " --- " + "Type: " + "Hero");
				else if(game.getSecondPlayer().getTeam().get(0) instanceof Villain)
					p2champ1.setText(p2champ1.getText() + " --- " + "Type: " + "Villain");
				else 
					p2champ1.setText(p2champ1.getText() + " --- " + "Type: " + "Anti-Hero");

				if(game.getSecondPlayer().getTeam().get(0).getName().equals(game.getSecondPlayer().getLeader().getName()))
					p2champ1.setText(p2champ1.getText() + " --- " + "Leader Status: Yes");
				else
					p2champ1.setText(p2champ1.getText() + " --- " + "Leader Status: No");

			effects = new JLabel("Applied Effects: ");
			for(Effect effect : game.getSecondPlayer().getTeam().get(0).getAppliedEffects()){
				effects.setText(effects.getText() + effect.getName() + "(" + Integer.toString(effect.getDuration()) + ") --- ");
				}
					
			effects.setForeground(Color.CYAN);
			effects.setFont(new Font("Serif", Font.PLAIN, 17));
			effects.setBounds(0, 0, all.getWidth(), 305);

			p2champ1.setForeground(Color.CYAN);
			p2champ1.setFont(new Font("Serif", Font.PLAIN, 17));
			p2champ1.setBounds(0, 0, all.getWidth(), 255);

			all.add(effects);
			all.add(p2champ1);
			all.repaint();
			all.revalidate();
		}
		if (e.getSource() == player2_c2) {
			// p2champ2.setEnabled(true);
			p2champ2 = new JLabel("Name: " + game.getSecondPlayer().getTeam().get(1).getName() + " --- " + "Hp: "
					+ Integer.toString(game.getSecondPlayer().getTeam().get(1).getCurrentHP()) + " --- " + "Mana: "
					+ Integer.toString(game.getSecondPlayer().getTeam().get(1).getMana()) + " " + "Action Points: "
					+ Integer.toString(game.getSecondPlayer().getTeam().get(1).getCurrentActionPoints()) + " --- "
					+ "Attack Damage: " + Integer.toString(game.getSecondPlayer().getTeam().get(1).getAttackDamage())
					+ " --- " + "Attack Range: "
					+ Integer.toString(game.getSecondPlayer().getTeam().get(1).getAttackRange())
					+ " --- " + "Speed: " + Integer.toString(game.getSecondPlayer().getTeam().get(1).getSpeed()));

				if(game.getSecondPlayer().getTeam().get(1) instanceof Hero)
					p2champ2.setText(p2champ2.getText() + " --- " + "Type: " + "Hero");
				else if(game.getSecondPlayer().getTeam().get(1) instanceof Villain)
					p2champ2.setText(p2champ2.getText() + " --- " + "Type: " + "Villain");
				else 
					p2champ2.setText(p2champ2.getText() + " --- " + "Type: " + "Anti-Hero");

				if(game.getSecondPlayer().getTeam().get(1).getName().equals(game.getSecondPlayer().getLeader().getName()))
					p2champ2.setText(p2champ2.getText() + " --- " + "Leader Status: Yes");
				else
					p2champ2.setText(p2champ2.getText() + " --- " + "Leader Status: No");

			effects = new JLabel("Applied Effects: ");
			for(Effect effect : game.getSecondPlayer().getTeam().get(1).getAppliedEffects()){
				effects.setText(effects.getText() + effect.getName() + "(" + Integer.toString(effect.getDuration()) + ") --- ");
				}
							
			effects.setForeground(Color.CYAN);
			effects.setFont(new Font("Serif", Font.PLAIN, 17));
			effects.setBounds(0, 0, all.getWidth(), 305);

			p2champ2.setForeground(Color.CYAN);
			p2champ2.setFont(new Font("Serif", Font.PLAIN, 17));
			p2champ2.setBounds(0, 0, all.getWidth(), 255);

			all.add(effects);
			all.add(p2champ2);
			all.repaint();
			all.revalidate();
		}
		if (e.getSource() == player2_c3) {
			// p2champ3.setEnabled(true);
			p2champ3 = new JLabel("Name: " + game.getSecondPlayer().getTeam().get(2).getName() + " --- " + "Hp: "
					+ Integer.toString(game.getSecondPlayer().getTeam().get(2).getCurrentHP()) + " --- " + "Mana: "
					+ Integer.toString(game.getSecondPlayer().getTeam().get(2).getMana()) + " --- " + "Action Points: "
					+ Integer.toString(game.getSecondPlayer().getTeam().get(2).getCurrentActionPoints()) + " --- "
					+ "Attack Damage: " + Integer.toString(game.getSecondPlayer().getTeam().get(2).getAttackDamage())
					+ " --- " + "Attack Range: "
					+ Integer.toString(game.getSecondPlayer().getTeam().get(2).getAttackRange())
					+ " --- " + "Speed: " + Integer.toString(game.getSecondPlayer().getTeam().get(2).getSpeed()));

				if(game.getSecondPlayer().getTeam().get(2) instanceof Hero)
					p2champ3.setText(p2champ3.getText() + " --- " + "Type: " + "Hero");
				else if(game.getSecondPlayer().getTeam().get(2) instanceof Villain)
					p2champ3.setText(p2champ3.getText() + " --- " + "Type: " + "Villain");
				else 
					p2champ3.setText(p2champ3.getText() + " --- " + "Type: " + "Anti-Hero");

				if(game.getSecondPlayer().getTeam().get(2).getName().equals(game.getSecondPlayer().getLeader().getName()))
					p2champ3.setText(p2champ3.getText() + " --- " + "Leader Status: Yes");
				else
					p2champ3.setText(p2champ3.getText() + " --- " + "Leader Status: No");

			effects = new JLabel("Applied Effects: ");
			for(Effect effect : game.getSecondPlayer().getTeam().get(2).getAppliedEffects()){
				effects.setText(effects.getText() + effect.getName() + "(" + Integer.toString(effect.getDuration()) + ") --- ");
				}
							
			effects.setForeground(Color.CYAN);
			effects.setFont(new Font("Serif", Font.PLAIN, 17));
			effects.setBounds(0, 0, all.getWidth(), 300);

			p2champ3.setForeground(Color.CYAN);
			p2champ3.setFont(new Font("Serif", Font.PLAIN, 17));
			p2champ3.setBounds(0, 0, all.getWidth(), 250);
			

			all.add(effects);
			all.add(p2champ3);
			all.repaint();
			all.revalidate();
		}


	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == player1_c1)
			all.remove(p1champ1);
		if(e.getSource() == player1_c2)
			all.remove(p1champ2);
		if(e.getSource() == player1_c3)
			all.remove(p1champ3);
		if(e.getSource() == player2_c1)
			all.remove(p2champ1);
		if(e.getSource() == player2_c2)
			all.remove(p2champ2);
		if(e.getSource() == player2_c3)
			all.remove(p2champ3);
		
		all.remove(effects);
		all.repaint();
		all.revalidate();

	}

	// public static void main(String[] args) {
	// Game.loadAbilities("Abilities.csv");
	// Game.loadChampions("Champions.csv");
	// Player nah = new Player("NAH");
	// Player woah = new Player("WOAH");
	// nah.getTeam().add(Game.getAvailableChampions().get(0));
	// nah.getTeam().add(Game.getAvailableChampions().get(1));
	// nah.getTeam().add(Game.getAvailableChampions().get(2));
	// nah.setLeader(nah.getTeam().get(0));
	//
	// System.out.println(nah.getTeam().get(2).getName());
	//
	// woah.getTeam().add(Game.getAvailableChampions().get(3));
	// woah.getTeam().add(Game.getAvailableChampions().get(4));
	// woah.getTeam().add(Game.getAvailableChampions().get(5));
	// woah.setLeader(woah.getTeam().get(0));
	//
	//
	//
	//
	//
	// Game game = new Game(nah, woah);
	// new Board(game);
	// }
	private boolean hasEffect(Champion currentChampion, String s) {
		for (Effect e : currentChampion.getAppliedEffects()) {
			if (e.getName().equals(s))
				return true;
		}
		return false;
	}

}
