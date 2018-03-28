package com.assignment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/automatedParking")
public class AutoParkingSystem {
	public static final int maxX = 14;
	public static final int maxY = 14;
	public Direction direction;
	public int currentX = 0;
	public int currentY = 0;
	
	private final Logger log = LoggerFactory.getLogger(AutoParkingSystem.class);
	
	public enum Direction {
		NORTH, SOUTH, EAST, WEST;
	}
	
	@GetMapping(value = "/park/{args}")
	public String park(@PathVariable String args) throws Exception{
		direction = Direction.NORTH;
		log.debug("input Arguments : " + args);
		String[] arguments = args.split(":");
		if(arguments.length != 2) throw new Exception("Invalid Arrguments");
		
		String[] initialXAndY = arguments[0].split(",");
		if(initialXAndY.length != 2) throw new Exception("Invalid Positions");
		
		log.debug("Initial Location -> " + initialXAndY[0]+","+initialXAndY[1]);
		
		currentX = Integer.parseInt(initialXAndY[0]);
		currentY = Integer.parseInt(initialXAndY[1]);
		
		if(currentX<0 || currentX >14) throw new Exception("X Position should be between 0 to 14");
		if(currentY<0 || currentY >14) throw new Exception("Y Position should be between 0 to 14");
		
		char[] parkingSeq = arguments[1].toCharArray();
		
		
		for(char seq : parkingSeq){
			switch(seq){
			case 'f':
			case 'F':
				log.debug("Moving Forward");
				move();
				break;
			case 'r':
			case 'l':
			case 'R':
			case 'L':	
				log.debug("Turning " + seq);
				changeDirection(seq);
				break;
			default:
				throw new Exception("Invalid Sequence: Sequence should be R, L and F");
			}
		}
		log.debug("Final Position: (" + (currentX) + "," + (currentY) +")");
		return "Current Position: (" + (currentX) + "," + (currentY) +")";
	}

	private void changeDirection(char turn) {
		log.debug("Current Direction : " + direction);
		
		if('R' == turn || 'r' == turn){
			turnRight();
			log.debug("Turning Right " + direction);
		}else if('L' == turn || 'l' == turn){
			turnLeft();
			log.debug("Turning Left " + direction);
		}
	}

	private void turnLeft() {
		switch(direction){
			case NORTH:
				direction = Direction.WEST;
				break;
			case SOUTH:
				direction = Direction.EAST;
				break;
			case WEST:
				direction = Direction.SOUTH;
				break;
			default:
				direction = Direction.NORTH;
		}
	}

	private void turnRight() {
		switch(direction){
			case NORTH:
				direction = Direction.EAST;
				break;
			case SOUTH:
				direction = Direction.WEST;
				break;
			case EAST:
				direction = Direction.SOUTH;
				break;
			default:
				direction = Direction.NORTH;
		}
		
	}

	private void move() throws Exception{
		switch(direction){
			case NORTH:
				currentX++;
				if(currentX< 0 || currentX > 14)throw new Exception("Over the boundary");
				break;
			case SOUTH:
				currentX--;
				if(currentX< 0 || currentX > 14)throw new Exception("Over the boundary");
				break;
			case EAST:
				currentY++;
				if(currentY< 0 || currentY > 14)throw new Exception("Over the boundary");
				break;
			case WEST:
				currentY--;
				if(currentY< 0 || currentY > 14)throw new Exception("Over the boundary");
				break;
		}
	}
}
