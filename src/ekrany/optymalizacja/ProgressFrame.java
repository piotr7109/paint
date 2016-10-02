package ekrany.optymalizacja;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dodatki.Tools;

public class ProgressFrame extends JFrame
{
	public ProgressPanel gui;

	public ProgressFrame()
	{
		//JFrame frame = new JFrame("Postęp");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		// Add content to the window.
		gui = new ProgressPanel();
		this.add(gui, BorderLayout.CENTER);

		// Display the window.
		this.pack();
		this.setVisible(true);
	}

	public void updateProgress(int progress)
	{
		gui.setProgress(progress);
	}

	public class ProgressPanel extends JPanel
	{
		private final int WIDTH = 200;
		private final int HEIGHT = 25;
		private int progress = 0;

		public ProgressPanel()
		{
			this.setPreferredSize(Tools.getDimension(WIDTH, HEIGHT));
		}

		public void setProgress(int progress)
		{
			this.progress = progress;
			this.paintComponent(this.getGraphics());
		}

		@Override
		protected void paintComponent(Graphics g)
		{
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, Tools.rescale(progress* WIDTH / 100 ), Tools.rescale(HEIGHT));
		}
	}
}
