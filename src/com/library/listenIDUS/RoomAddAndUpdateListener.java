/**
 * 
 */
package com.library.listenIDUS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import zouxin.library.dao.MainViewDao;
import zouxin.library.view.LibraryAddAndUpdate;
import zouxin.library.view.RoomAddAndUpdate;

/**
 * @author ZouXin
 *2017-5-11
 */
public class RoomAddAndUpdateListener implements ActionListener{
     RoomAddAndUpdate addAndUpdate;
     public RoomAddAndUpdateListener(RoomAddAndUpdate addAndUpdate){
    	 this.addAndUpdate=addAndUpdate;
     }
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		MainViewDao dao=new MainViewDao();
		String command=e.getActionCommand();
		String number=addAndUpdate.getTextField().getText();
		String name=addAndUpdate.getTextField_2().getText();
		String location=addAndUpdate.getTextField_3().getText();
		String introduction=addAndUpdate.getTextField_4().getText();
		String rlall=addAndUpdate.getComboBox().getSelectedItem().toString();
		String lnumber=rlall.split("_")[1];
		if(command.equals("确定")){
			if(addAndUpdate.getJudge()==0){
				int select=JOptionPane.showConfirmDialog(addAndUpdate, "是否确定添加");
				if(select==0){
					String sqll="select count(*) from library_room_information_table lit where lit.library_room_number=?";
					int judge=dao.selectCount(sqll, number);
					if(judge==0){
						String sqlI="insert into library_room_information_table values(?,?,?,?,?)";
						int s=dao.insertUpdateDelete(sqlI, number,lnumber,name,location,introduction);
						if(s==1){
							JOptionPane.showMessageDialog(addAndUpdate, "添加成功");
							if(addAndUpdate.getMainView().getTable_2().getSelectedRow()>=0){
								addAndUpdate.getMainView().setVisible(true);
								addAndUpdate.getMainView().getRoomSelect().doClick();
							}
							addAndUpdate.setVisible(false);
							addAndUpdate.dispose();
						}else{
							JOptionPane.showMessageDialog(addAndUpdate, "系统错误");
						}
					}else{
						JOptionPane.showMessageDialog(addAndUpdate, "该库位编号已存在，请重新输入");
					}
				}
			}else if(addAndUpdate.getJudge()==1){
				int select=JOptionPane.showConfirmDialog(addAndUpdate, "是否确定修改");
				if(select==0){
					    String sqlU="update library_room_information_table lrit set lrit.library_number=?,lrit.library_room_name=?,lrit.library_room_location=?,lrit.library_room_introduction=? where lrit.library_room_number=?";
						int s=dao.insertUpdateDelete(sqlU,lnumber,name,location,introduction,number);
						if(s==1){
							JOptionPane.showMessageDialog(addAndUpdate, "修改成功");
							addAndUpdate.getMainView().setVisible(true);
							addAndUpdate.getMainView().getRoomSelect().doClick();
							addAndUpdate.setVisible(false);
							addAndUpdate.dispose();
							
						}else{
							JOptionPane.showMessageDialog(addAndUpdate, "系统错误");
						}
					
				}
			}
		}else if(command.equals("取消")){
			addAndUpdate.setVisible(false);
			addAndUpdate.dispose();
		}
	}
      
}
