package jcbt.main;

import javax.swing.JPanel;

/**
 * CBTMain 프레임에 부착될 여러 패널들의 공통 상위 패널
 * 
 * @author Son Minho
 */
public class CBTPanel extends JPanel {

	/**
	 * 이 패널이 부착될 프레임의 참조변수
	 */
	protected CBTMain cbtMain = null;
	
	/**
	 * 프레임의 참조변수를 할당받는다.
	 * @param cbtMain
	 */
	public CBTPanel(CBTMain cbtMain) {
		this.cbtMain = cbtMain;
	}
	
	/**
	 * 프레임의 showMainContent메써드를 호출하여 Card Layout으로 구성된 여러 패널중
	 * 다른 패널을 보여준다.
	 * @param panelName
	 */
	public void changeMainContent(String panelName) {
		cbtMain.showMainContent(panelName);
	}
}
