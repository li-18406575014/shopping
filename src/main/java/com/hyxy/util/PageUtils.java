package com.hyxy.util;
public class PageUtils {
	// ��ǰҳĬ�ϵ�1ҳ����ҳ���ȡ�ĵ�ǰҳ�룬δ���㣩
	private int currentPage = 1;
	// ǰһҳ
	private int prevPage;
	// ��һҳ
	private int nextPage;
	// βҳ
	private int lastPage;
	// �ܼ�¼��
	private int count;
	// ÿҳ������,Ĭ��10��
	private int pageSize = 10;
	// ��ʼ��¼������ҳ��������ʹ��ʱ���õĳ�ʼҳ�������
	private int pageRecord;
	// ҳ���ҳhtmlģ�ʹ���
	private String page;
 
	// �вι�����
	public PageUtils(String currentPage, int count, String pageSize) {
		init(currentPage, count, pageSize);
		initLastPage();
		initCurrentPage();
		initPrevPage();
		initNextPage();
		initPageRecord();
		initPage();
	}
 
	// ��ʼ��������ҪԪ��
	private void init(String currentPage, int count, String pageSize) {
		if (currentPage != null && currentPage != "" && currentPage != "0") {
			this.currentPage = Integer.parseInt(currentPage);
		}
		
		this.count = count;
		
		if (pageSize != null && pageSize != "" && pageSize != "0") {
			this.pageSize = Integer.parseInt(pageSize);;
		}
	}
 
	// ��ʼ��βҳ
	private void initLastPage() {
		if (count % pageSize == 0) {
			lastPage = count / pageSize;
		} else {
			lastPage = count / pageSize + 1;
		}
	}
 
	// ��ʼ����������ǰҳ(��ֹ�ⲿ���ʳ���)
	private void initCurrentPage() {
		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > lastPage) {
			currentPage = lastPage;
		}
	}
 
	// ��ʼ����һҳ
	private void initPrevPage() {
		if (currentPage != 1) {
			prevPage = currentPage - 1;
		}else{
			prevPage = 1;
		}
	}
 
	// ��ʼ����һҳ
	private void initNextPage() {
		if (currentPage != lastPage) {
			nextPage = currentPage + 1;
		}else{
			nextPage = lastPage;
		}
	}
 
	// ��ʼ����ҳ������
	private void initPageRecord() {
		pageRecord = (currentPage - 1) * pageSize;
        if(pageRecord < 0){
            pageRecord = 0;
        }
	}
 
	// ��ʼ��htmlҳ���ҳģ�ʹ���
	private void initPage() {
		page = "<div style='text-align: right;'>";
		page += "��" + currentPage + "/" + lastPage + "ҳ����" + count + "����¼��";
		page += "<select style='width: 70px;' id='pageSize' onchange='page(1)'><option>1</option><option>2</option><option>5</option></select>";
		page += "<input type='button' value='��ҳ' onclick='page(1)' />";
		page += "<input type='button' value='��һҳ' onclick='page(" + prevPage + ")' />";
		page += "<input type='button' value='��һҳ' onclick='page(" + nextPage + ")' />";
		page += "<input type='button' value='βҳ' onclick='page(" + lastPage + ")' />";
		page += "</div>";
	}
 
	/*
	 * �������ͨ��
	 */
	public int getCurrentPage() {
		return currentPage;
	}
 
	public int getPrevPage() {
		return prevPage;
	}
 
	public int getNextPage() {
		return nextPage;
	}
 
	public int getLastPage() {
		return lastPage;
	}
 
	public int getCount() {
		return count;
	}
 
	public int getPageSize() {
		return pageSize;
	}
 
	public int getPageRecord() {
		return pageRecord;
	}
 
	public String getPage() {
		return page;
	}
 
}
