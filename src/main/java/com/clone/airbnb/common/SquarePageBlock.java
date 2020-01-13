package com.clone.airbnb.common;

import org.springframework.data.domain.Page;

import lombok.Getter;

/**
 * <p>Page 객체를 이용하여 Page 블락을 생성한다.
 * 
 * <ul>페이지 블락형식(사이즈 10)
 * 	<li>첫 번째 블락 - 1 2 3 4 5 6 7 8 9 10
 * 	<li>두 번째 블락 - 11 12 13 14 15 16 17 18 19 20
 * 	<li>세 번재 블락 - 21 22 23 24 25 26 27 28 29 30
 * 
 * @author jjw
 *
 */
@Getter
public class SquarePageBlock implements IPageBlock {
	
	private final int first;
	private final int last;
	
	public SquarePageBlock(Page<?> page, int blockSize) {
		if (blockSize < 1) blockSize = 10;
		
		int nowBlock = page.getNumber() / blockSize;
		
		int first = nowBlock * blockSize + 1;
		int last = first + blockSize - 1;

		if (last > page.getTotalPages()) {
			last = page.getTotalPages();
		}
		
		this.first = first;
		this.last = last;
	}
	
}
