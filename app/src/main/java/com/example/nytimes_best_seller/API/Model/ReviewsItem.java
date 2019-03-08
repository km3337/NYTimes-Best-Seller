package com.example.nytimes_best_seller.API.Model;

import com.google.gson.annotations.SerializedName;

public class ReviewsItem{

	@SerializedName("article_chapter_link")
	private String articleChapterLink;

	@SerializedName("book_review_link")
	private String bookReviewLink;

	@SerializedName("first_chapter_link")
	private String firstChapterLink;

	@SerializedName("sunday_review_link")
	private String sundayReviewLink;

	public void setArticleChapterLink(String articleChapterLink){
		this.articleChapterLink = articleChapterLink;
	}

	public String getArticleChapterLink(){
		return articleChapterLink;
	}

	public void setBookReviewLink(String bookReviewLink){
		this.bookReviewLink = bookReviewLink;
	}

	public String getBookReviewLink(){
		return bookReviewLink;
	}

	public void setFirstChapterLink(String firstChapterLink){
		this.firstChapterLink = firstChapterLink;
	}

	public String getFirstChapterLink(){
		return firstChapterLink;
	}

	public void setSundayReviewLink(String sundayReviewLink){
		this.sundayReviewLink = sundayReviewLink;
	}

	public String getSundayReviewLink(){
		return sundayReviewLink;
	}

	@Override
 	public String toString(){
		return 
			"ReviewsItem{" + 
			"article_chapter_link = '" + articleChapterLink + '\'' + 
			",book_review_link = '" + bookReviewLink + '\'' + 
			",first_chapter_link = '" + firstChapterLink + '\'' + 
			",sunday_review_link = '" + sundayReviewLink + '\'' + 
			"}";
		}
}