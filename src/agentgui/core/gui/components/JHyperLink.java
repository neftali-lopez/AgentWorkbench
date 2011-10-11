/**
 * ***************************************************************
 * Agent.GUI is a framework to develop Multi-agent based simulation 
 * applications based on the JADE - Framework in compliance with the 
 * FIPA specifications. 
 * Copyright (C) 2010 Christian Derksen and DAWIS
 * http://www.dawis.wiwi.uni-due.de
 * http://sourceforge.net/projects/agentgui/
 * http://www.agentgui.org 
 *
 * GNU Lesser General Public License
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation,
 * version 2.1 of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA  02111-1307, USA.
 * **************************************************************
 */
package agentgui.core.gui.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import javax.swing.JLabel;

public class JHyperLink extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8351025656123572661L;

	protected final Color LINK_COLOR = Color.blue;
	protected LinkedList<ActionListener> actionListenerList = new LinkedList<ActionListener>();
	protected boolean underline;

	protected MouseListener mouseListener = new MouseAdapter() {

		public void mouseEntered(MouseEvent me) {
			underline = true;
			repaint();
		}

		public void mouseExited(MouseEvent me) {
			underline = false;
			repaint();
		}

		public void mouseClicked(MouseEvent me) {
			fireActionEvent();
		}
	};

	public JHyperLink() {
		this("");
	}

	public JHyperLink(String text) {
		super(text);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setForeground(LINK_COLOR);
		addMouseListener(mouseListener);
	}

	public void addActionListener(ActionListener l) {
		if (!actionListenerList.contains(l)) {
			actionListenerList.add(l);
		}
	}

	public void removeActionListener(ActionListener l) {
		actionListenerList.remove(l);
	}

	protected void fireActionEvent() {
		ActionEvent e = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, getText());
		for (ActionListener l : actionListenerList) {
			l.actionPerformed(e);
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (underline) {
			// really all this size stuff below only needs to be recalculated if
			// font or text changes
			Rectangle2D textBounds = getFontMetrics(getFont()).getStringBounds( getText(), g);

			// this layout stuff assumes the icon is to the left, or null
			int y = getHeight() / 2 + (int) (textBounds.getHeight() / 2);
			int w = (int) textBounds.getWidth();
			int x = getIcon() == null ? 0 : getIcon().getIconWidth() + getIconTextGap();

			g.setColor(getForeground());
			g.drawLine(0, y, x + w, y);
		}
	}
}
