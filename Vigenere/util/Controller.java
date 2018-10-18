package util;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.*;
import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Image;
import music.*;
import gui.*;
import util.*;
import javax.swing.event.*;
import java.awt.event.*;

import java.util.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;

public class Controller implements MP3Listener, ItemListener, ListSelectionListener, SongListener //, ControllerListener,  
{	
	private Music music;

	private ArtistsView artist_view;
	private CDsView CD_lsit;
	private SongView song_view;
	private ImageView image_view;
	private MP3View mp3_view;
	private MP3 mp3;

	private ArrayList<Controllable> views;

	private Thread thread;

  public void dragEnter(DropTargetDragEvent e){}
  public void dragExit(DropTargetEvent e){}
  public void dragOver(DropTargetDragEvent e){}
  public void dropActionChanged(DropTargetDragEvent e){}

	public void play(Song song)
	{
		//mp3_view.update((Song)song_view.getSelectedValue());
		mp3 = mp3.setMP3(mp3_view.getSelectedMp3());
		
		if(thread.getState() != Thread.State.TERMINATED)
		{
			mp3.mp3Done();
		}

        else if(song != null)
        { 
          thread = new Thread(mp3);
          thread.start();
        }

	}

	// Observer pattern
	public void mp3Done()
	{
		System.out.println("Song stopped playing.");
	}

	public void valueChanged(ListSelectionEvent e){}

	public void artistChanged(Artist artist)
	{
		Iterator<CD> itr = artist.iterator();
		Controllable artC = views.get(0);
		Controllable cds = views.get(1);
		//Artist art = (Artist)artC.getSelectedItem();
		cds.update();
	}

	public void itemStateChanged(ItemEvent e)
	{
		//Controllable art = views.get(0);
		//Controllable view = views.get(1);
		//Controllable cd = views.get(1);
		Controllable art = views.get(0);
		//art.up(e);
		//Artist selected_artist = (Artist)getSelectedItem();//(Artist)art.
        //Iterator<CD> CDs = selected_artist.iterator();

		//if(e.getSource().equals(art) && e.getStateChange() == e.SELECTED)
		/*
		{
			for(Controllable view : views)
			{
				view.update(e);
			}	

			//CD selected_CD = (CD)cd.getSelected();
			//song_view.update(selected_CD);

			//image_view.update(selected_CD);
		}
		*/
		/*
		else if(e.getSource().equals(CD_lsit) && e.getStateChange() == e.SELECTED)
		{
			CD selected_CD = (CD)CD_lsit.getSelectedItem();
			//song_view.update(selected_CD);
			//image_view.update(selected_CD);

			Song a = (Song)song_view.getSelectedValue();
		}
		*/
	}
/*
	public void itemStateChanged(ItemEvent e)
	{
		
		Controllable cd = views.get(1);

		if(e.getSource().equals(artist_view) && e.getStateChange() == e.SELECTED)
		{
			Artist selected_artist = (Artist)artist_view.getSelectedItem();
			Iterator<CD> artists = selected_artist.iterator();
			cd.update(artists);
			
			CD selected_CD = (CD)CD_lsit.getSelectedItem();
			song_view.update(selected_CD);

			image_view.update(selected_CD);
		}
		
		else if(e.getSource().equals(CD_lsit) && e.getStateChange() == e.SELECTED)
		{
			CD selected_CD = (CD)CD_lsit.getSelectedItem();
			//song_view.update(selected_CD);
			//image_view.update(selected_CD);

			Song a = (Song)song_view.getSelectedValue();
		}

	}
*/
	/*
	public Controller(Music music, ArtistsView art, CDsView cds, SongView song, ImageView image, MP3View mp3view)
	{      
   		this.music = music;
   		views = new ArrayList<Controllable>();

   		//artist_view = art;
   		views.add(art);

   		System.out.println("AAAA");
   		//CD_lsit = cds;
   		views.add(cds);

   		song_view = song;
   		image_view = image;
   		mp3_view = mp3view;
   		thread = new NullThread();
   		thread.start();
   		mp3 = new MP3(mp3_view.getSelectedMp3());
   		mp3.register(this);

   		Iterator<Artist> artists = music.iterator();
   		art.initialize(artists);

   	}*/

   		public Controller(Music music, ArtistsView art, CDsView cds, SongView song, ImageView image, MP3View mp3view)
	{      
   		this.music = music;
   		views = new ArrayList<Controllable>();

   		//artist_view = art;
   		views.add(art);

   		//CD_lsit = cds;
   		
   		views.add(cds);

   		//song_view = song;
   		views.add(song);

   		image_view = image;
   		mp3_view = mp3view;
   		thread = new NullThread();
   		thread.start();
   		mp3 = new MP3(mp3_view.getSelectedMp3());
   		mp3.register(this);

   		addListeners();

   		Iterator<Artist> artists = music.iterator();
   		art.initialize(artists);

   	}
 /*
   	public void initialize()
   	{
   		
   		//artist_view.addListener(this);

   		for(Controllable view : views)
   		{
   			view.addListener(this);
   		}
   		
   		Controllable art = views.get(0);
   		art.addListener(this);
   		// Initialize CD List
   		
		Controllable cd = views.get(0);
		cd.addListener(this);
		
		// Initialize Song List
 		

 		mp3_view.initialize(this);

 		// Initialize
 		
 		Iterator<Artist> artists = music.iterator();
   		artist_view.initialize(artists);
   		
   		Controllable at = views.get(0);
		at.initialize(artists);
		
		
   	}
*/
   	public void addListeners()
   	{
  		for(Controllable view : views)
   		{
   			view.addListener(this);
   		}		

 		mp3_view.initialize(this);

 		// Initialize
 		/*
 		Iterator<Artist> artists = music.iterator();
   		artist_view.initialize(artists);
   		
   		Controllable at = views.get(0);
		at.initialize(artists);
		*/
		
   	}

   	public Artist getSelectedArtist()
   	{
   		return artist_view.getSelectedArtist();
   	}

	public CD getSelectedCD()
	{
		return CD_lsit.getSelectedCD();
	}

	public void update()
	{
		artist_view.removeAllItems();
		CD_lsit.removeAllItems();
	}
}