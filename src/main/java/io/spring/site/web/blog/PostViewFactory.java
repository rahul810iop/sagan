package io.spring.site.web.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.spring.site.domain.blog.Post;
import io.spring.site.domain.services.DateService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostViewFactory {
	private DateService dateService;

	@Autowired
	public PostViewFactory(DateService dateService) {
		this.dateService = dateService;
	}

	public PostView createPostView(Post post) {
		return new PostView(post, dateService);
	}

	public List<PostView> createPostViewList(List<Post> posts) {
		List<PostView> postViews = new ArrayList<PostView>();
		for (Post post : posts) {
			postViews.add(createPostView(post));
		}
		return postViews;
	}

	public Page<PostView> createPostViewPage(Page<Post> posts) {
		List<PostView> postViews = createPostViewList(posts.getContent());
		PageRequest pageRequest = new PageRequest(posts.getNumber(), posts.getSize(), posts.getSort());
		return new PageImpl<PostView>(postViews, pageRequest, posts.getTotalElements());
	}
}