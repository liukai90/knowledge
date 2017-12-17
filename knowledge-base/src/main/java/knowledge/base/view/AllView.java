package knowledge.base.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import knowledge.base.pojo.ClassAndEntity;
import knowledge.base.pojo.Entity;
import knowledge.base.pojo.EntityAndAttributeData;
import knowledge.base.service.FindService;
import knowledge.base.service.TableSplitService;
import knowledge.base.util.StringUtil;

public class AllView {
	
	private JFrame f;
	
	private JPanel  p;
	
	private JLabel label,label2;
	
	private JButton button1,button2;
	
	private JTextField tx1,tx2;
	
	public void fristPage(){
				
		f=new JFrame("首页");
		
		f.setLayout(new GridLayout(2,3));
		
		label=new JLabel("输入知识：");
		
		label2=new JLabel("输入表名;");
		
		tx1=new JTextField(30);
		
		tx2=new JTextField(30);
		
		button1=new JButton("搜索");
		
		button2=new JButton("增加新知识");
				
		button1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				String class_=tx1.getText().trim();
				
				FindService findService=new FindService();
		
					try {
						long start=System.currentTimeMillis();
						
						List<ClassAndEntity> classAndEntities=findService.getClassAndEntitiesByClass(class_);
						
						if(classAndEntities!=null&&classAndEntities.size()>0){
							
							new AllView().classPage(classAndEntities);
						}else{
							
							List<EntityAndAttributeData> entityAndAttributeDatas=
									findService.getEntityAndAttributeDatasByObject(class_+":");
							
							new AllView().entityPage(entityAndAttributeDatas);
							
							
						}
						
						long end=System.currentTimeMillis();
						
						new AllView().state("耗时："+(end-start)+"ms");
						
					} catch (Exception e) {
						e.printStackTrace();
						
						new AllView().state("执行失败");
					}
				
				
				
				
			}
		});
		
		button2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String tableName=tx2.getText().trim();
				
				TableSplitService tableSplitService=new TableSplitService();
				
				try {
					
					long start=System.currentTimeMillis();
					
					tableSplitService.insertImpose(tableName);
					
					tableSplitService.insertObjectAttribute(tableName);
					
					tableSplitService.insertClassAttribute(tableName);
					
					tableSplitService.insertClassTable(tableName);
					
					tableSplitService.insertEntity(tableName);
					
					tableSplitService.insertAttributeData(tableName);
					
					long end=System.currentTimeMillis();
					
					new AllView().state("执行成功!\n"+"耗时："+(end-start)+"ms");
					
				} catch (Exception e1) {
					
					e1.printStackTrace();
					
					new AllView().state("执行失败");
				}
			}
		});
				
		f.setSize(400, 300);
		
		f.setLocationRelativeTo(null);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.add(label);
		
		f.add(tx1);
		
		f.add(button1);
		
		f.add(label2);
		
		f.add(tx2);
		
		f.add(button2);
		f.setVisible(true);
		
	}

	public void classPage(List<ClassAndEntity> classAndEntities){
		
		Integer rows=classAndEntities.size();
		
		f=new JFrame("搜索结果");
		
		f.setLayout(new GridLayout(rows+1,4));
		
		f.setSize(1000, 800);
		
		f.setLocationRelativeTo(null);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.add(new JLabel("类"));
		
		f.add(new JLabel("父类"));
		
		f.add(new JLabel("子类"));
		
		f.add(new JLabel("实体"));
		
		for(ClassAndEntity classAndEntity:classAndEntities){
			
			f.add(new JLabel(classAndEntity.getClass_()));
			
			f.add(new JLabel(classAndEntity.getSuperclass()));
			
			f.add(new JLabel(classAndEntity.getSubclass()));
			
			if(classAndEntity.getEntity()!=null){
				
				f.add(new JLabel(classAndEntity.getEntity()));
			}else{
				
				f.add(new JLabel("无"));
			}
		}
		
		f.setVisible(true);
	}
	
	public void entityPage(List<EntityAndAttributeData> entityAndAttributeDatas){
		
		Integer rows=entityAndAttributeDatas.size();
		
		f=new JFrame("搜索结果");
		
		f.setLayout(new GridLayout(rows+1,3));
		
		f.setSize(1000, 1000);
		
		f.setLocationRelativeTo(null);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.add(new JLabel("对象"));
		
		f.add(new JLabel("属性"));
		
		f.add(new JLabel("属性值"));
		
		for(EntityAndAttributeData entityAndAttributeData:entityAndAttributeDatas){
			
			f.add(new JLabel(entityAndAttributeData.getObject()));
			
			if(entityAndAttributeData.getProperty()!=null){
				f.add(new JLabel(entityAndAttributeData.getProperty()));
			}else{
				
				f.add(new JLabel("无"));
			}
			
			if(entityAndAttributeData.getValue()!=null){
				f.add(new JLabel(entityAndAttributeData.getValue()));
				
			}else{
				
				f.add(new JLabel("无"));
			}
		}
		f.setVisible(true);
	}
	
	public void state(String str){
		
		f=new JFrame("状态");
		
		f.setSize(300, 150);
				
		f.setLocationRelativeTo(null);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.add(new JLabel(str));
		
		f.setVisible(true);
	}
}
