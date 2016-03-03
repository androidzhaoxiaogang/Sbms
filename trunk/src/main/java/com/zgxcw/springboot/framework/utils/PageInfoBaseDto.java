package com.zgxcw.springboot.framework.utils;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class PageInfoBaseDto<T> implements Serializable {

  private static final long serialVersionUID = 1L;
  // 当前页
  private int pageNum;
  // 每页的数量
  private int pageSize;
  // 当前页的数量
  private int size;
  // 当前页面第一个元素在数据库中的行号
  private int startRow;
  // 由于startRow和endRow不常用，这里说个具体的用法
  // 可以在页面中"显示startRow到endRow 共size条数据"
  // 当前页面最后一个元素在数据库中的行号
  private int endRow;
  // 总记录数
  private long total;
  // 总页数
  private int pages;
  // 结果集
  private Collection<T> list;
  // 临时存储列表
  private List<T> tempList;
  // 第一页
  private int firstPage;
  // 前一页
  private int prePage;
  // 下一页
  private int nextPage;
  // 最后一页
  private int lastPage;
  // 是否为第一页
  private boolean isFirstPage = false;
  // 是否为最后一页
  private boolean isLastPage = false;
  // 是否有前一页
  private boolean hasPreviousPage = false;
  // 是否有下一页
  private boolean hasNextPage = false;
  // 导航页码数
  private int navigatePages;
  // 所有导航页号
  private int[] navigatepageNums;

  public PageInfoBaseDto() {
  }

  /**
   * 当前页
   */
  public int getPageNum() {
    return pageNum;
  }

  /**
   * 当前页
   * 
   * @param @param pageNum
   */
  public void setPageNum(int pageNum) {
    this.pageNum = pageNum;
  }

  /**
   * 每页的数量
   */
  public int getPageSize() {
    return pageSize;
  }

  /**
   * 每页的数量
   * 
   * @param @param pageSize
   */
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  /**
   * 当前页的数量
   */
  public int getSize() {
    return size;
  }

  /**
   * 当前页的数量
   * 
   * @param @param size
   */
  public void setSize(int size) {
    this.size = size;
  }

  /**
   * 当前页面第一个元素在数据库中的行号
   */
  public int getStartRow() {
    return startRow;
  }

  /**
   * 当前页面第一个元素在数据库中的行号
   * 
   * @param @param startRow
   */
  public void setStartRow(int startRow) {
    this.startRow = startRow;
  }

  /**
   * 当前页面最后一个元素在数据库中的行号
   */
  public int getEndRow() {
    return endRow;
  }

  /**
   * 当前页面最后一个元素在数据库中的行号
   * 
   * @param @param endRow
   */
  public void setEndRow(int endRow) {
    this.endRow = endRow;
  }

  /**
   * 总记录数
   */
  public long getTotal() {
    return total;
  }

  /**
   * 总记录数
   * 
   * @param @param total
   */
  public void setTotal(long total) {
    this.total = total;
  }

  /**
   * 总页数
   */
  public int getPages() {
    return pages;
  }

  /**
   * 总页数
   * 
   * @param @param pages
   */
  public void setPages(int pages) {
    this.pages = pages;
  }

  /**
   * 结果集
   */
  public Collection<T> getList() {
    return list;
  }

  /**
   * 结果集
   * 
   * @param @param list
   */
  public void setList(Collection<T> list) {
    this.list = list;
  }

  /**
   * 第一页
   */
  public int getFirstPage() {
    return firstPage;
  }

  /**
   * 第一页
   * 
   * @param @param firstPage
   */
  public void setFirstPage(int firstPage) {
    this.firstPage = firstPage;
  }

  /**
   * 前一页
   */
  public int getPrePage() {
    return prePage;
  }

  /**
   * 前一页
   * 
   * @param @param prePage
   */
  public void setPrePage(int prePage) {
    this.prePage = prePage;
  }

  /**
   * 下一页
   */
  public int getNextPage() {
    return nextPage;
  }

  /**
   * 下一页
   * 
   * @param @param nextPage
   */
  public void setNextPage(int nextPage) {
    this.nextPage = nextPage;
  }

  /**
   * 最后一页
   */
  public int getLastPage() {
    return lastPage;
  }

  /**
   * 最后一页
   * 
   * @param @param lastPage
   */
  public void setLastPage(int lastPage) {
    this.lastPage = lastPage;
  }

  /**
   * 是否为第一页
   */
  public boolean isIsFirstPage() {
    return isFirstPage;
  }

  /**
   * 是否为第一页
   */
  public void setIsFirstPage(boolean isFirstPage) {
    this.isFirstPage = isFirstPage;
  }

  /**
   * 是否为最后一页
   */
  public boolean isIsLastPage() {
    return isLastPage;
  }

  /**
   * 是否为最后一页
   * */
  public void setIsLastPage(boolean isLastPage) {
    this.isLastPage = isLastPage;
  }

  /**
   * 是否有前一页
   * */
  public boolean isHasPreviousPage() {
    return hasPreviousPage;
  }

  /**
   * 是否有前一页
   * */
  public void setHasPreviousPage(boolean hasPreviousPage) {
    this.hasPreviousPage = hasPreviousPage;
  }

  /**
   * 是否有下一页
   * */
  public boolean isHasNextPage() {
    return hasNextPage;
  }

  /**
   * 是否有下一页
   */
  public void setHasNextPage(boolean hasNextPage) {
    this.hasNextPage = hasNextPage;
  }

  /**
   * 导航页码数
   */
  public int getNavigatePages() {
    return navigatePages;
  }

  /**
   * 导航页码数
   */
  public void setNavigatePages(int navigatePages) {
    this.navigatePages = navigatePages;
  }

  /**
   * 所有导航页号
   */
  public int[] getNavigatepageNums() {
    return navigatepageNums;
  }

  /**
   * 所有导航页号
   */
  public void setNavigatepageNums(int[] navigatepageNums) {
    this.navigatepageNums = navigatepageNums;
  }
  
  public List<T> getTempList() {
    return tempList;
  }

  public void setTempList(List<T> tempList) {
    this.tempList = tempList;
  }

  /**
   * 
   * @param pageNumber 当前页数
   * @param pageSize 步长
   * @param totalCount 列表总条数
   */
  public void setPagerParameter(int pageNumber,int pageSize,int totalCount){
		this.setPageNum(pageNumber);//当前页数
		this.setPageSize(pageSize); //每页显示数
		this.setTotal(totalCount); // 数据总条数
		this.setStartRow((pageNumber - 1) * pageSize + 1);//当前页面第一个元素在数据库中的行号
		this.setEndRow(this.getStartRow() + pageSize);// 当前页面最后一个元素在数据库中的行号
		this.setPages((int)Math.ceil((double)totalCount / pageSize));//总页数
		this.setFirstPage(1);//第一页
		this.setPrePage(pageNumber == 1 ? 1 : (pageNumber-1));// 前一页
		this.setNextPage(pageNumber == this.getPages() ? pageNumber : (pageNumber + 1));// 下一页
		this.setLastPage(this.getPages());//最后一页
		this.setIsFirstPage(pageNumber == 1);//是否为第一页
		this.setIsLastPage(pageNumber == this.getPages());//是否为最后一页
		this.setHasPreviousPage(pageNumber != 1);// 是否有前一页
		this.setHasNextPage(pageNumber != this.getPages());//是否有后一页
		this.setNavigatePages(8);//可显示多少页数
		//导航页码
		calcNavigatepageNums();
	}
  /**
   * 计算导航页
   */
  private void calcNavigatepageNums() {
      //当总页数小于或等于导航页码数时
      if (pages <= navigatePages) {
          navigatepageNums = new int[pages];
          for (int i = 0; i < pages; i++) {
              navigatepageNums[i] = i + 1;
          }
      } else { //当总页数大于导航页码数时
          navigatepageNums = new int[navigatePages];
          int startNum = pageNum - navigatePages / 2;
          int endNum = pageNum + navigatePages / 2;

          if (startNum < 1) {
              startNum = 1;
              //(最前navigatePages页
              for (int i = 0; i < navigatePages; i++) {
                  navigatepageNums[i] = startNum++;
              }
          } else if (endNum > pages) {
              endNum = pages;
              //最后navigatePages页
              for (int i = navigatePages - 1; i >= 0; i--) {
                  navigatepageNums[i] = endNum--;
              }
          } else {
              //所有中间页
              for (int i = 0; i < navigatePages; i++) {
                  navigatepageNums[i] = startNum++;
              }
          }
      }
  }
  
}