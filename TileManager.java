//Andrew Liu
//Programming Assignment 11
//Create a class which manages the tiles in TileMain
//TileManager
//Version 1.0
//3/20/2013
import java.util.*;
import java.awt.*;
public class TileManager
{
	/**
	* ArrayList<Tile> tiles is the array list which stores all the tiles
	*/
	private ArrayList<Tile> tiles;
	/**
	* Tile testTile is the tile which is used to compare x and y values of the mouse
	*/
	private Tile testTile;
	/**
	* int counter keeps count in the for loops
	*/
	private int counter;
	/**
	* int tracker keeps track of which tile to raise, lower, or delete
	*/
	private int tracker;
	/**
	* boolean test determines whether or not to raise, lower, or delete a tile
	*/
	private boolean test;
	/**
	* constructor, initializes class variables
	*/
	public TileManager()
	{
		test = false;
		counter = 0;
		tracker = -1;
		tiles = new ArrayList<Tile>();
	}
	/**
	* adds a tile to the end of the array list
	* @param Tile rect is the tile to be added
	*/
	public void addTile(Tile rect)
	{
		tiles.add(rect);//adds a rectangle
	}
	/**
	* draws all the tiles
	* @param Graphics g is the graphics used in drawing panel
	*/
	public void drawAll(Graphics g)
	{
		counter = 0;
		for(Tile t : tiles)
		{
			tiles.get(counter).draw(g);//draws the tile
			counter++;
		}
	}
	/**
	* raises a tile if the mouse is in the right location
	* @param int x is the x coordinate of the mouse
	* @param int y is the y coordinate of the mouse
	*/
	public void raise(int x, int y)
	{
		tracker = trackInt(x,y);
		if(tracker != -1)
		{
			tiles.add(testTile);//add the tile to the end of the list
			tiles.remove(tracker);//removes the tile at the specified index
		}
	}
	/**
	* lowers a tile if the mouse is in the right location and shift+clicks
	* @param int x is the x coordinate of the mouse
	* @param int y is the y coordinate of the mouse
	*/
	public void lower(int x, int y)
	{
		tracker = trackInt(x,y);
		if(tracker != -1)
		{
			tiles.add(0, testTile);//adds a tile at the beginning of the list
			tiles.remove(tracker + 1);//removes the tile from the previous location
		}
	}
	/**
	* deletes the tile if the mouse is in the right location
	* @param int x is the x coordinate of the mouse
	* @param int y is the y coordinate of the mouse
	*/
	public void delete(int x, int y)
	{
		tracker = trackInt(x,y);
		if(tracker != -1)
		{
			tiles.remove(tracker);//removes the tile at the specified index
		}
	}
	/**
	* deletes all the tiles which the mouse is over
	* @param int x is the x coordinate of the mouse
	* @param int y is the y coordinate of the mouse
	*/
	public void deleteAll(int x, int y)
	{
		int remove = -1;
		test = false;
		ArrayList<Integer> track = new ArrayList<Integer>();//tracks which tiles to delete
		counter = 0;
		tracker = -1;
		for(Tile t : tiles)
		{
			t = tiles.get(counter);
			if(x >= t.getX() && x <= t.getX() + t.getWidth() && y >= t.getY() && y <= t.getY() + t.getHeight())//checks whether or not the mouse is over the tile 
			{
				testTile = tiles.get(counter);//gets the tile at the index
				tracker = counter;
				test = true;//if the mouse is over teh tile
				track.add(tracker);//adds the index to track
			}
			counter++;
		}
		counter = 0;
		for(int ii : track)
		{
			remove = track.get(counter);
			tiles.remove(remove - counter);//indexes change depending on how many tiles have been removed
			counter++;
		}
	}
	/**
	* shuffles the tiles randomly
	* @param int width is the width of the drawing panel
	* @param int height is the height of the drawing panel
	*/
	public void shuffle(int width, int height)
	{
		Random r = new Random();
		Collections.shuffle(tiles);//shuffles the tiles
		for(Tile ttt : tiles)
		{
			ttt.setX(r.nextInt(width - ttt.getWidth()));//randomizes the position of the tile
			ttt.setY(r.nextInt(height - ttt.getHeight()));
		}
	}
	/**
	* returns the index of the tile to alter
	* @param int x is the x coordinate of the mouse
	* @param int y is the y coordinate of the mouse
	* @return int is the index of the tile to be adjusted
	*/
	public int trackInt(int x, int y)
	{
		test = false;
		counter = 0;
		tracker = -1;
		for(Tile t : tiles)
		{
			t = tiles.get(counter);
			if(x >= t.getX() && x <= t.getX() + t.getWidth() && y >= t.getY() && y <= t.getY() + t.getHeight())//if the mouse is over the tile
			{
				testTile = tiles.get(counter);//gets tile at the index
				tracker = counter;//sets tracker equal to the last index where the mouse was over a tile
				test = true;
			}
			counter++;
		}
		return tracker;
	}
}