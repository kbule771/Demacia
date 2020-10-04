package com.web.blog.model.service.user;

import java.util.ArrayList;
import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.web.blog.model.dao.post.PostDao;
import com.web.blog.model.dao.user.UserDao;
import com.web.blog.model.dao.vote.VoteDao;
import com.web.blog.model.dto.post.PostDetail;
import com.web.blog.model.dto.user.User;
import com.web.blog.model.dto.user.UserVote;
import com.web.blog.model.dto.vote.Vote;
import com.web.blog.model.service.vote.VoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private VoteDao voteDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private VoteService VoteService;

    @Override
    public User login(String userEmail, String userPw) {
        User res = new User();
        User res2 = new User();
        res = dao.login(userEmail, userPw);

        if (res != null) {
            return res;
        } else {
            if (dao.userByEmail(userEmail) != 0) {
                res2.setUserEmail("password failed");
            } else {
                res2.setUserEmail("id failed");
            }
            return res2;
        }

    }

    @Override
    public int userInsert(User user) {

        return dao.userInsert(user);

    }

    @Override
    public User userSearch(int userNo) {
        return dao.userSearch(userNo);
    }

    @Override
    public int userUpdate(User user) {
        int res = dao.userUpdate(user);

        if (res != 0) {
            res = 1;
        } else {
            res = 0;
        }

        return res;
    }

    @Override
    public int userDelete(int userNo) {

        User user = dao.userSearch(userNo);
        int res = 0;

        if (user != null) {
            res = dao.userDelete(user.getUserNo());
            if (res != 0) { // 삭제성공
                res = 1;
            } else {
                res = 0;
            }
        }

        return res;
    }

    @Override
    public String userFindEmail(String user) {
        return userFindEmail(user);
    }

    @Override
    public String userFindPW(String userEmail, String userNickname) {
        return dao.userFindPW(userEmail, userNickname);
    }

    @Override
    public int userByNickname(String userNickname) {
        return dao.userByNickname(userNickname);
    }

    @Override
    public int userByEmail(String userEmail) {
        return dao.userByEmail(userEmail);
    }

    @Override
    public User socialuserByEmail(String userEmail) {
        User user = dao.socialuserByEmail(userEmail);
        if (user != null) {
            return user;
        }
        return null;
    }

    @Override
    public int socialuserInsert(User user) {
        try {
            int res = dao.socialuserInsert(user);
            return res;
        } catch (Exception e) {
            return 0;
        }
    }

    // 투표 이력
    @Override
    public ArrayList<UserVote> userVote(int userNo) {
        ArrayList<UserVote> uservote = new ArrayList<>();
        // userNo 으로 투표 이력 들고오기
        ArrayList<Vote> votes = voteDao.userVote(userNo);

        // 들고온 투표내역에 postNo의 결과 확인하기 (for문 활용)
        for (Vote v : votes) {
            int postNo = v.getPostNo(); // 투표내역의 게시물 no
            int value = v.getValue(); // 어느 진영에 투표했는가

            // 해당 게시물의 상세 정보
            PostDetail postDetail = postDao.postDetail(postNo);
            String postTitle = postDetail.getTitle();
            int point = postDetail.getMileage();

            // 게시물의 투표 결과 가져오기
            int isFinished = postDetail.getIsFinished();

            // 결과를 맞춘여부
            String voteResult = "";
            if (isFinished == value || isFinished == 3) {
                voteResult = "Win";
            } else if (isFinished == 0) {
                voteResult = "Proceeding";
            } else {
                voteResult = "Lose";
            }

            // 해당 값들을 uservote에 넣어 리턴하기
            uservote.add(new UserVote(userNo, postNo, postTitle, voteResult, point));
        }

        return uservote;
    }

    // 투표 적중률
    @Override
    public Double userHitRate(int userNo) {
        return dao.userHitRate(userNo);
    };

    // 이메일 송신
    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public int sendMail(User user) throws MessagingException {
        System.out.println(user.getUserEmail());
        System.out.println(user.getUserNickname());
        try {
            final MimeMessage message = javaMailSender.createMimeMessage();
            message.setSubject("데마시아 회원가입을 축하합니다");
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getUserEmail()));
            message.setText(user.getUserNickname() + " 님의 가입을 축하드립니다!!!");
            message.setSentDate(new Date());
            javaMailSender.send(message);
            System.out.println(message);
            return 1;
        } catch (MessagingException e) {
            e.printStackTrace();
            return 0;
        }
    }

}