package jcbt.main;

import javax.swing.JPanel;

/**
 * CBTMain �����ӿ� ������ ���� �гε��� ���� ���� �г�
 * 
 * @author Son Minho
 */
public class CBTPanel extends JPanel {

	/**
	 * �� �г��� ������ �������� ��������
	 */
	protected CBTMain cbtMain = null;
	
	/**
	 * �������� ���������� �Ҵ�޴´�.
	 * @param cbtMain
	 */
	public CBTPanel(CBTMain cbtMain) {
		this.cbtMain = cbtMain;
	}
	
	/**
	 * �������� showMainContent�޽�带 ȣ���Ͽ� Card Layout���� ������ ���� �г���
	 * �ٸ� �г��� �����ش�.
	 * @param panelName
	 */
	public void changeMainContent(String panelName) {
		cbtMain.showMainContent(panelName);
	}
}
