package util;

import music.*;
import java.util.*;
import java.awt.event.*;

public interface Controllable<Listener>
{
	public void addListener(Listener l);
	public void update();
	//public E getSelectedItem();
}