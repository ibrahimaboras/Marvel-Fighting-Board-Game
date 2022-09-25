package GUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import engine.*;
import model.abilities.Ability;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.world.Champion;


public class Start extends JFrame implements MouseListener{

	private JFrame player_names;
	
	private Player first;
	private Player second;
	
	private int choose = 0;
	public int place1 = 0;
	private int place2 = 0;
	
	private JPanel panel1;
	private JPanel start;
	private JPanel first_player_champions;
	private JPanel second_player_champions;
	private JPanel first_player_leader;
	private JPanel second_player_leader;
	private ImagePanel panel2;
	private JPanel availableChampions;

	private JPanel blue;
	
	private JLabel image;
	private JLabel start_quit;
	private ImagePanel champion_description;
	private JLabel temp = new JLabel();
	private JLabel first_name;
	private JLabel second_name;
	private JLabel player1_name;
	private JLabel player2_name;
	private JLabel champion_select;
	private JLabel pick_leader;
	
	private JButton multi;
	private JButton quit;
	private JButton add;
	private JButton next;
	private JButton play;
	
	private JTextField enter_first;
	private JTextField enter_second;
	
	private ImageIcon marvel;
	
	private ArrayList<ImageIcon> champions_images;
	
	private ArrayList<JButton> champions;
	private ArrayList<JButton> player1_choose_leader;
	private ArrayList<JButton> player2_choose_leader;
	
	private ArrayList<JLabel> temp_leader_1;
	private ArrayList<JLabel> temp_leader_2;
	
	public Start() throws IOException {
		
		champion_select = new JLabel("Choose Your Champions");
		champion_select.setForeground(Color.white);
		champion_select.setBounds(325, 10, 350, 50);
		champion_select.setFont(new Font("Serrif", Font.PLAIN, 30));
		pick_leader = new JLabel("Pick Your Leaders");
		pick_leader.setForeground(Color.white);
		pick_leader.setBounds(365, 10, 350, 50);
		pick_leader.setFont(new Font("Serrif", Font.PLAIN, 30));
		pick_leader.setVisible(false);
		
		first_player_leader = new JPanel();
		first_player_leader.setLayout(new GridLayout(3, 1));
		first_player_leader.setBounds(140, 100, 140, 300);
		first_player_leader.setFocusable(false);
		first_player_leader.setOpaque(false);
		first_player_leader.setVisible(false);
		
		second_player_leader = new JPanel();
		second_player_leader.setLayout(new GridLayout(3, 1));
		second_player_leader.setBounds(720, 100, 140, 300);
		second_player_leader.setFocusable(false);
		second_player_leader.setOpaque(false);
		second_player_leader.setVisible(false);
		
		first_player_champions = new JPanel();
		first_player_champions.setLayout(new GridLayout(1, 3));
		first_player_champions.setBounds(0, 0, 250, 50);
		first_player_champions.setFocusable(false);
		first_player_champions.setOpaque(false);
		
		second_player_champions = new JPanel();
		second_player_champions.setLayout(new GridLayout(1, 3));
		second_player_champions.setBounds(750, 0, 250, 50);
		second_player_champions.setFocusable(false);
		second_player_champions.setOpaque(false);
		
		player_names = new JFrame();
		player_names.setResizable(false);
		player_names.setBounds(0, 0, 400, 200);
		player_names.getContentPane().setBackground(Color.black);
		player_names.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		player_names.setLayout(null);
		player_names.setLocationRelativeTo(null);
		
		first_name = new JLabel("First Player Name");
		first_name.setForeground(Color.white);
		first_name.setBounds(138, 10, 200, 10);
		
		second_name = new JLabel("Second Player Name:");
		second_name.setForeground(Color.white);
		second_name.setBounds(138, 10, 200, 10);
		second_name.setVisible(false);
		
		next = new JButton("Next");
		next.setForeground(Color.white);
		next.setContentAreaFilled(false);
		next.setOpaque(false);
		next.setFocusable(false);
		next.setBounds(140, 115, 100, 40);
//		String khara = "";
//		String khara2 = "";
		
		next.addActionListener((e) -> {
			
			first_name.setVisible(false);
			first = new Player(enter_first.getText());
			next.setVisible(false);
			enter_first.setVisible(false);
			
			second_name.setVisible(true);
			play.setVisible(true);
			enter_second.setVisible(true);
			
		});
		
		play = new JButton("Play");
		play.setForeground(Color.white);
		play.setContentAreaFilled(false);
		play.setOpaque(false);
		play.setFocusable(false);
		play.setBounds(140, 115, 100, 40);
		play.addActionListener((e) -> {
			
			player_names.setVisible(false);
			second = new Player(enter_second.getText());
			start.setVisible(false);
			panel2.setVisible(true);
			this.add(availableChampions);
			
			for(JButton x : champions) 
				availableChampions.add(x);
			
		});
		play.setVisible(false);
		
		enter_first = new JTextField("Enter your name");
		enter_first.setHorizontalAlignment(JTextField.CENTER);
		enter_first.setForeground(Color.white);
		enter_first.setBounds(15, 40, 350, 40);
		enter_first.setOpaque(false);
		
		enter_second = new JTextField("Enter your name");
		enter_second.setHorizontalAlignment(JTextField.CENTER);
		enter_second.setForeground(Color.white);
		enter_second.setBounds(15, 40, 350, 40);
		enter_second.setOpaque(false);
		enter_second.setVisible(false);
		
		player_names.add(first_name);
		player_names.add(second_name);
		player_names.add(next);
		player_names.add(play);
		player_names.add(enter_first);
		player_names.add(enter_second);
		
		this.setIconImage(new ImageIcon("icon.png").getImage());
		this.setTitle("Marvel");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(450, 200);
		this.setLayout(null);
		this.setSize(1015, 638);
		this.setResizable(false);
	 
		Game.loadAbilities("Abilities.csv");
		Game.loadChampions("Champions.csv");
		
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
		
		champions = new ArrayList<JButton>();
		
		for(ImageIcon i : champions_images) {
			JButton temp = new JButton();
			temp.setFocusable(false);
			temp.setOpaque(false);
			temp.setContentAreaFilled(false);
			//temp.setBorderPainted(false);
			Image temp_image = i.getImage();
			temp_image = temp_image.getScaledInstance(120, 85, java.awt.Image.SCALE_SMOOTH);
			i = new ImageIcon(temp_image);
			temp.setIcon(i);;
			champions.add(temp);
		}
		
		ImageIcon marvel_temp = new ImageIcon("Marvel Logo.png");
		Image image1 = marvel_temp.getImage();
		image1 = image1.getScaledInstance(500, 200, java.awt.Image.SCALE_SMOOTH);
		marvel = new ImageIcon();
		marvel.setImage(image1);
		
		
		image = new JLabel(marvel);
		image.setText("Press Anywhere To Start");
		image.setHorizontalTextPosition(JLabel.CENTER);
		image.setVerticalTextPosition(JLabel.BOTTOM);
		image.setForeground(Color.gray);
		image.setFont(new Font("Serrif", Font.PLAIN, 20));
		image.setIconTextGap(50);
		image.setBounds(200, 150, 550, 300);
		
		multi = new JButton("Play");
		multi.setFont(new Font("Serrif", Font.PLAIN, 16));
		multi.setForeground(Color.gray);
		multi.setBounds(330, 375, 300, 40);
		multi.setFocusable(false);
		multi.setOpaque(false);
		multi.setContentAreaFilled(false);
		multi.addActionListener((e) -> {
			
			player_names.setVisible(true);
			
//			start.setVisible(false);
//			panel2.setVisible(true);
//			this.add(availableChampions);
//			
//			for(JButton x : champions) 
//				availableChampions.add(x);
			
		});
		
		ImageIcon description_icon = new ImageIcon("blue.jpg");
		Image description_image = description_icon.getImage();
		description_image = description_image.getScaledInstance(600, 330, java.awt.Image.SCALE_SMOOTH);
		
		champion_description = new ImagePanel(description_image);
		champion_description.setLayout(null);
		add = new JButton();
		
//		System.out.println(first.getName());
		
//		player1_name = new JLabel(first.getName());
//		player1_name.setBounds(0, 55, 50, 10);
//		player1_name.setBackground(Color.white);
//		
//		player2_name = new JLabel(second.getName());
//		player2_name.setBounds(750, 55, 50, 10);
//		player2_name.setBackground(Color.white);
		
//		panel2.add(player1_name);
//		panel2.add(player2_name);
		
		temp_leader_1 = new ArrayList<JLabel>();
		temp_leader_2 = new ArrayList<JLabel>();
		
		for(JButton x : champions) {
			x.addActionListener((e) -> {
						
				champion_description.setVisible(true);
				add.setVisible(false);
				add = new JButton("Add Champion");
				
				panel2.remove(temp);
				champion_description.removeAll();
				
				ImageIcon image_temp = (ImageIcon) x.getIcon();
				Image image_temp1 = image_temp.getImage();
				image_temp1 = image_temp1.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
				ImageIcon final_image = new ImageIcon();
				final_image.setImage(image_temp1);
				
				temp = new JLabel(final_image);
				temp.setSize(temp.getPreferredSize());
				temp.setLocation(165, 250);
				temp.setVisible(true);
				panel2.add(temp);
				panel2.repaint();
				
				Champion champ = Game.getAvailableChampions().get(champions.indexOf(x));
				
				JLabel name = new JLabel("Name:");
				name.setForeground(Color.red);
				name.setFont(new Font("Serrif", Font.PLAIN, 16));
				name.setBounds(10, 0, 70, 30);
				JLabel champ_name = new JLabel(champ.getName());
				champ_name.setForeground(Color.WHITE);
				champ_name.setFont(new Font("Serrif", Font.PLAIN, 16));
				champ_name.setBounds(65, 0, 150, 30);
				
				JLabel hp = new JLabel("HP:");
				hp.setForeground(Color.red);
				hp.setFont(new Font("Serrif", Font.PLAIN, 16));
				hp.setBounds(10, 20, 70, 30);
				JLabel maxhp = new JLabel(Integer.toString(champ.getMaxHP()));
				maxhp.setForeground(Color.WHITE);
				maxhp.setFont(new Font("Serrif", Font.PLAIN, 16));
				maxhp.setBounds(45, 20, 70, 30);
				
				JLabel mana = new JLabel("Mana:");
				mana.setForeground(Color.red);
				mana.setFont(new Font("Serrif", Font.PLAIN, 16));
				mana.setBounds(10, 40, 70, 30);
				JLabel maxmana = new JLabel(Integer.toString(champ.getMana()));
				maxmana.setForeground(Color.WHITE);
				maxmana.setFont(new Font("Serrif", Font.PLAIN, 16));
				maxmana.setBounds(65, 40, 70, 30);
				
				JLabel actionpoints = new JLabel("Action Points:");
				actionpoints.setForeground(Color.red);
				actionpoints.setFont(new Font("Serrif", Font.PLAIN, 16));
				actionpoints.setBounds(10, 60, 150, 30);
				JLabel maxactionpoints = new JLabel(Integer.toString(champ.getMaxActionPointsPerTurn()));
				maxactionpoints.setForeground(Color.WHITE);
				maxactionpoints.setFont(new Font("Serrif", Font.PLAIN, 16));
				maxactionpoints.setBounds(123, 60, 70, 30);
				
				JLabel attackdamage = new JLabel("Attack Damage:");
				attackdamage.setForeground(Color.red);
				attackdamage.setFont(new Font("Serrif", Font.PLAIN, 16));
				attackdamage.setBounds(10, 80, 150, 30);
				JLabel maxattacakdamage = new JLabel(Integer.toString(champ.getAttackDamage()));
				maxattacakdamage.setForeground(Color.WHITE);
				maxattacakdamage.setFont(new Font("Serrif", Font.PLAIN, 16));
				maxattacakdamage.setBounds(135, 80, 70, 30);
				
				JLabel attackrange = new JLabel("Attack Range:");
				attackrange.setForeground(Color.red);
				attackrange.setFont(new Font("Serrif", Font.PLAIN, 16));
				attackrange.setBounds(10, 100, 150, 30);
				JLabel maxattackrange = new JLabel(Integer.toString(champ.getAttackRange()));
				maxattackrange.setForeground(Color.WHITE);
				maxattackrange.setFont(new Font("Serrif", Font.PLAIN, 16));
				maxattackrange.setBounds(123, 100, 70, 30);
				
				JLabel speed = new JLabel("Speed:");
				speed.setForeground(Color.red);
				speed.setFont(new Font("Serrif", Font.PLAIN, 16));
				speed.setBounds(10, 120, 150, 30);
				JLabel maxspeed = new JLabel(Integer.toString(champ.getSpeed()));
				maxspeed.setForeground(Color.WHITE);
				maxspeed.setFont(new Font("Serrif", Font.PLAIN, 16));
				maxspeed.setBounds(65, 120, 70, 30);
				
				champion_description.add(name);
				champion_description.add(champ_name);
				champion_description.add(hp);
				champion_description.add(maxhp);
				champion_description.add(mana);
				champion_description.add(maxmana);
				champion_description.add(actionpoints);
				champion_description.add(maxactionpoints);
				champion_description.add(attackdamage);
				champion_description.add(maxattacakdamage);
				champion_description.add(attackrange);
				champion_description.add(maxattackrange);
				champion_description.add(speed);
				champion_description.add(maxspeed);
				
				int i = 1;
				int j = 140;
				int q = 10;
				
				for(Ability a : champ.getAbilities()) {
					
					JLabel ability_name = new JLabel("Ability " + i +  " Name:");
					ability_name.setForeground(Color.red);
					ability_name.setFont(new Font("Serrif", Font.PLAIN, 16));
					ability_name.setBounds(q, j, 150, 30);
					JLabel actual_ability_name = new JLabel(a.getName());
					actual_ability_name.setForeground(Color.WHITE);
					actual_ability_name.setFont(new Font("Serrif", Font.PLAIN, 16));
					actual_ability_name.setBounds(q + 130, j, 200, 30);
					
					JLabel ability_manacost = new JLabel("Ability " + i + " Mana Cost:");
					ability_manacost.setForeground(Color.red);
					ability_manacost.setFont(new Font("Serrif", Font.PLAIN, 16));
					ability_manacost.setBounds(q, j + 20, 200, 30);
					JLabel actual_ability_manacost = new JLabel(Integer.toString(a.getManaCost()));
					actual_ability_manacost.setForeground(Color.WHITE);
					actual_ability_manacost.setFont(new Font("Serrif", Font.PLAIN, 16));
					actual_ability_manacost.setBounds(q + 167, j + 20, 70, 30);
					
					JLabel ability_cooldown = new JLabel("Ability " + i + " Cooldown:");
					ability_cooldown.setForeground(Color.red);
					ability_cooldown.setFont(new Font("Serrif", Font.PLAIN, 16));
					ability_cooldown.setBounds(q, j + 40, 200, 30);
					JLabel actual_ability_cooldown = new JLabel(Integer.toString(a.getBaseCooldown()));
					actual_ability_cooldown.setForeground(Color.WHITE);
					actual_ability_cooldown.setFont(new Font("Serrif", Font.PLAIN, 16));
					actual_ability_cooldown.setBounds(q + 155, j + 40, 70, 30);
					
					JLabel ability_cast_range = new JLabel("Ability " + i + " Cast Range:");
					ability_cast_range.setForeground(Color.red);
					ability_cast_range.setFont(new Font("Serrif", Font.PLAIN, 16));
					ability_cast_range.setBounds(q, j + 60, 200, 30);
					JLabel actual_ability_cast_range = new JLabel(Integer.toString(a.getCastRange()));
					actual_ability_cast_range.setForeground(Color.WHITE);
					actual_ability_cast_range.setFont(new Font("Serrif", Font.PLAIN, 16));
					actual_ability_cast_range.setBounds(q + 171, j + 60, 70, 30);
					
					JLabel ability_cast_area = new JLabel("Ability " + i + " Cast Area:");
					ability_cast_area.setForeground(Color.red);
					ability_cast_area.setFont(new Font("Serrif", Font.PLAIN, 16));
					ability_cast_area.setBounds(q, j + 80, 200, 30);
					JLabel actual_ability_cast_area = new JLabel(a.getCastArea().name());
					actual_ability_cast_area.setForeground(Color.WHITE);
					actual_ability_cast_area.setFont(new Font("Serrif", Font.PLAIN, 16));
					actual_ability_cast_area.setBounds(q + 162, j + 80, 150, 30);
					
					JLabel ability_required_action_points = new JLabel("Ability " + i + " Required Action Points:");
					ability_required_action_points.setForeground(Color.red);
					ability_required_action_points.setFont(new Font("Serrif", Font.PLAIN, 16));
					ability_required_action_points.setBounds(q, j + 100, 300, 30);
					JLabel actual_ability_required = new JLabel(Integer.toString(a.getRequiredActionPoints()));
					actual_ability_required.setForeground(Color.WHITE);
					actual_ability_required.setFont(new Font("Serrif", Font.PLAIN, 16));
					actual_ability_required.setBounds(q + 260, j + 100, 150, 30);
	
					champion_description.add(ability_name);
					champion_description.add(actual_ability_name);
					champion_description.add(ability_manacost);
					champion_description.add(actual_ability_manacost);
					champion_description.add(ability_cooldown);
					champion_description.add(actual_ability_cooldown);
					champion_description.add(ability_cast_range);
					champion_description.add(actual_ability_cast_range);
					champion_description.add(ability_cast_area);
					champion_description.add(actual_ability_cast_area);
					champion_description.add(ability_required_action_points);
					champion_description.add(actual_ability_required);
					
					if(a instanceof CrowdControlAbility) {
						
						JLabel effect_name = new JLabel("Ability Effect Name:");
						effect_name.setForeground(Color.red);
						effect_name.setFont(new Font("Serrif", Font.PLAIN, 16));
						effect_name.setBounds(q, j + 120, 300, 30);
						JLabel actual_effect_name = new JLabel(((CrowdControlAbility)a).getEffect().getName());
						actual_effect_name.setForeground(Color.WHITE);
						actual_effect_name.setFont(new Font("Serrif", Font.PLAIN, 16));
						actual_effect_name.setBounds(q + 163, j + 120, 150, 30);
						
//						JLabel effect_type = new JLabel("Ability Effect Type");
//						effect_type.setForeground(Color.red);
//						JLabel actual_effect_type = new JLabel(((CrowdControlAbility)a).getEffect().getType().name());
						
						JLabel duration = new JLabel("Ability Effect Duration:");
						duration.setForeground(Color.red);
						duration.setFont(new Font("Serrif", Font.PLAIN, 16));
						duration.setBounds(q, j + 140, 300, 30);
						JLabel actual_duration = new JLabel(Integer.toString(((CrowdControlAbility)a).getEffect().getDuration()));
						actual_duration.setForeground(Color.WHITE);
						actual_duration.setFont(new Font("Serrif", Font.PLAIN, 16));
						actual_duration.setBounds(q + 185, j + 140, 150, 30);
						
						champion_description.add(effect_name);
						champion_description.add(actual_effect_name);
						champion_description.add(duration);
						champion_description.add(actual_duration);
						
					}
					
					else if(a instanceof DamagingAbility) {
						
						JLabel damage = new JLabel("Ability Damaging Amount:");
						damage.setForeground(Color.red);
						damage.setFont(new Font("Serrif", Font.PLAIN, 16));
						damage.setBounds(q, j + 120, 300, 30);
						JLabel actual_damage = new JLabel(Integer.toString(((DamagingAbility)a).getDamageAmount()));
						actual_damage.setForeground(Color.WHITE);
						actual_damage.setFont(new Font("Serrif", Font.PLAIN, 16));
						actual_damage.setBounds(q + 205, j + 120, 150, 30);
						
						champion_description.add(damage);
						champion_description.add(actual_damage);
						
					}
					
					else { 
						
						JLabel healing = new JLabel("Ability Healing Amount:");
						healing.setForeground(Color.red);
						healing.setFont(new Font("Serrif", Font.PLAIN, 16));
						healing.setBounds(q, j + 120, 300, 30);
						JLabel actual_healing = new JLabel(Integer.toString(((HealingAbility)a).getHealAmount()));
						actual_healing.setForeground(Color.WHITE);
						actual_healing.setFont(new Font("Serrif", Font.PLAIN, 16));
						actual_healing.setBounds(q + 185, j + 120, 150, 30);
						
						champion_description.add(healing);
						champion_description.add(actual_healing);
						
					}
		
					i++;
					if(i == 2) {
						
						q = 300;
						j = 0;
						
					}
					
					if(i == 3) j = 160;
					
				}
				
				champion_description.setBounds(320, 80, 600, 330);
				
				panel2.add(champion_description);
				
				add.setFocusable(false);
				add.setOpaque(false);
				add.setContentAreaFilled(false);
				add.setForeground(Color.green);
				add.setFont(new Font("Serrif", Font.PLAIN, 16));
				add.setBounds(0, 150, 200, 100);
				
				add.addActionListener((a) -> {
					x.setEnabled(false);
					add.setVisible(false);
					champion_description.setVisible(false);
					champion_description.removeAll();
					panel2.remove(temp);
					panel2.repaint();
					
					if(choose == 0) {
						
						first.getTeam().add(champ);
						
						ImageIcon champion_temp = (ImageIcon) x.getIcon();
						Image champion_temp1 = champion_temp.getImage();
						champion_temp1 = champion_temp1.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
						ImageIcon champion_final_image = new ImageIcon();
						champion_final_image.setImage(champion_temp1);
						
						JLabel temp1= new JLabel(champion_final_image);
						temp1.setSize(temp.getPreferredSize());
						temp1.setLocation(165, 250);
						
//						temp_leader_1 = new ArrayList<JLabel>();
						temp_leader_1.add(temp);
						
						JButton p = player1_choose_leader.get(place1);
						p.setFocusable(false);
						p.setContentAreaFilled(false);
						p.setOpaque(false);
							
							p.setIcon(final_image);
							first_player_leader.add(p);
							p.addActionListener((ae) -> {
								
									first.setLeader(first.getTeam().get(player1_choose_leader.indexOf(p)));
									for(JButton ao : player1_choose_leader) ao.setEnabled(false);
									
									if(first.getLeader() != null && second.getLeader() != null) {
										
										first.setLeader(first.getTeam().get(player1_choose_leader.indexOf(p)));
										first_player_leader.setEnabled(false);
										first_player_leader.setVisible(false);
										second_player_leader.setVisible(false);
										pick_leader.setVisible(false);
										panel2.remove(pick_leader);
										panel2.remove(first_player_leader);
										panel2.remove(second_player_leader);
										
										JButton fight = new JButton("FIGHT!");
										fight.setFocusable(false);
										fight.setOpaque(false);
										fight.setContentAreaFilled(false);
										fight.setForeground(Color.GREEN);
										fight.setFont(new Font("Serrif", Font.PLAIN, 40));
										fight.setBounds(350, 150, 300, 150);
										fight.addActionListener((f) -> {
											this.dispose();
											new Board(new Game(first, second));
										});
										panel2.add(fight);
										
									}
//									place1++;
									
								
								
							});
							place1++;
						
						
						player1_name = new JLabel(first.getName());
						player1_name.setBounds(0, 55, 50, 15);
						
						player1_name = new JLabel(first.getName());
						player1_name.setBounds(0, 55, 200, 15);
						player1_name.setForeground(Color.white);
						
						player2_name = new JLabel(second.getName());
						player2_name.setBounds(750, 55, 200, 15);
						player2_name.setForeground(Color.white);
						
						panel2.add(player1_name);
						panel2.add(player2_name);
						first_player_champions.add(temp1);
						choose = 1;
						
					}
					
					else {
						
						second.getTeam().add(champ);
						
						ImageIcon champion_temp = (ImageIcon) x.getIcon();
						Image champion_temp1 = champion_temp.getImage();
						champion_temp1 = champion_temp1.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
						ImageIcon champion_final_image = new ImageIcon();
						champion_final_image.setImage(champion_temp1);
						
						JLabel temp1= new JLabel(champion_final_image);
						temp1.setSize(temp.getPreferredSize());
						temp1.setLocation(165, 250);
						
//						temp_leader_2 = new ArrayList<JLabel>();
						temp_leader_2.add(temp);
						
						JButton p = player2_choose_leader.get(place2);
						p.setFocusable(false);
						p.setContentAreaFilled(false);
						p.setOpaque(false);
						
						p.setIcon(final_image);
						second_player_leader.add(p);
						p.addActionListener((ae) -> {
								
								second.setLeader(second.getTeam().get(player2_choose_leader.indexOf(p)));
								for(JButton ao : player2_choose_leader) ao.setEnabled(false);
								
								if(first.getLeader() != null && second.getLeader() != null) {
									
									second.setLeader(second.getTeam().get(player2_choose_leader.indexOf(p)));
									first_player_leader.setVisible(false);
									second_player_leader.setVisible(false);
									pick_leader.setVisible(false);
									panel2.remove(first_player_leader);
									panel2.remove(second_player_leader);
									
									JButton fight = new JButton("FIGHT!");
									fight.setFocusable(false);
									fight.setOpaque(false);
									fight.setContentAreaFilled(false);
									fight.setForeground(Color.GREEN);
									fight.setFont(new Font("Serrif", Font.PLAIN, 40));
									fight.setBounds(350, 150, 300, 150);
									fight.addActionListener((f) -> {
										this.dispose();
										new Board(new Game(first, second));
									});
									panel2.add(fight);
									
								}
//								place2++;
								
							
							
						});
						
						place2++;
						second_player_champions.add(temp1);
						choose = 0;
						
					}
					
					if(first.getTeam().size() == 3 && second.getTeam().size() == 3) {
						for(JButton disable : champions) 
							disable.setEnabled(false);
						
//						for(JButton enable : player1_choose_leader) 
//							enable.setEnabled(true);						
						champion_select.setVisible(false);
						pick_leader.setVisible(true);
						first_player_leader.setVisible(true);
						second_player_leader.setVisible(true);
						
//						JButton fight = new JButton("FIGHT!");
//						fight.setFocusable(false);
//						fight.setOpaque(false);
//						fight.setContentAreaFilled(false);
//						fight.setForeground(Color.black);
//						fight.setFont(new Font("Serrif", Font.PLAIN, 40));
//						fight.setBounds(350, 150, 300, 150);
//						fight.addActionListener((f) -> {
//							this.dispose();
//							new Board(new Game(first, second));
//						});
//						panel2.add(fight);
						
					}
				});
				add.setVisible(true);
				
				panel2.add(add);
				panel2.add(first_player_champions);
				panel2.add(second_player_champions);
				
//				player1_name = new JLabel(first.getName());
//				player1_name.setBounds(0, 55, 50, 10);
//				player1_name.setBackground(Color.white);
				
//				player2_name = new JLabel(second.getName());
//				player2_name.setBounds(750, 55, 50, 10);
//				player2_name.setBackground(Color.white);
				
//				panel2.add(player1_name);
				//panel2.add(player2_name);
				
			});
			
		}
		
		player1_choose_leader = new ArrayList<>();
		player2_choose_leader = new ArrayList<>();
		
		for(int i = 0; i < 3; i++) {
			
			JButton new_button1 = new JButton();
			JButton new_button2 = new JButton();
			
			player1_choose_leader.add(new_button1);
			player2_choose_leader.add(new_button2);
		}
		
		quit = new JButton("Quit");
		quit.setFont(new Font("Serrif", Font.PLAIN, 16));
		quit.setForeground(Color.gray);
		quit.setBounds(330, 450, 300, 40);
		quit.setFocusable(false);
		quit.setOpaque(false);
		quit.setContentAreaFilled(false);
		quit.addActionListener((e) -> System.exit(0));
		
		start_quit = new JLabel(marvel);
		start_quit.setBounds(200, 109, 550, 300);
		
		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBackground(Color.black);
		panel1.setSize(1000, 600);
		panel1.add(image);
		panel1.addMouseListener(this);
		
		start = new JPanel();
		start.setLayout(null);
		start.setBackground(Color.black);
		start.setSize(1000, 600);
		start.add(start_quit);
		start.add(multi);
		start.add(quit);
		start.setVisible(false);
		
		ImageIcon wall_icon = new ImageIcon("wall.jpg");
		Image wall = wall_icon.getImage();
		wall = wall.getScaledInstance(1000, 420, java.awt.Image.SCALE_SMOOTH);
		
		availableChampions = new JPanel();
		availableChampions.setBackground(new Color(116, 10, 1));
		availableChampions.setLayout(new GridLayout(2, 8, 10, 10));
		availableChampions.setOpaque(true);
//		availableChampions.setBackground(Color.green);
		availableChampions.setBounds(0, 420, 1000, 180);
		
		ImageIcon background_icon = new ImageIcon("background.jpg");
		Image background = background_icon.getImage();
		background = background.getScaledInstance(1000, 420, java.awt.Image.SCALE_SMOOTH);
		
		panel2 = new ImagePanel(background);
		panel2.setLayout(null);
		panel2.setBounds(0, 0, 1000, 420);
		panel2.add(champion_select);
		panel2.add(pick_leader);
		panel2.add(first_player_leader);
		panel2.add(second_player_leader);
		panel2.setVisible(false);
		
		
	
		this.setVisible(true);
		this.add(panel1);
		this.add(start);
		this.add(panel2);
		this.setLocationRelativeTo(null);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == panel1) {
			
		panel1.setVisible(false);
		start.setVisible(true);
		
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
