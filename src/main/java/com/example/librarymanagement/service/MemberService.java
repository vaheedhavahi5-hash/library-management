package com.example.librarymanagement.service;

import com.example.librarymanagement.entity.Member;
import com.example.librarymanagement.entity.BookIssue;
import com.example.librarymanagement.entity.BorrowingHistoryResponse;
import com.example.librarymanagement.repository.MemberRepository;
import com.example.librarymanagement.repository.BookIssueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BookIssueRepository bookIssueRepository;

    public MemberService(MemberRepository memberRepository,
                         BookIssueRepository bookIssueRepository) {
        this.memberRepository = memberRepository;
        this.bookIssueRepository = bookIssueRepository;
    }

    // Create Member
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    // Get All Members
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // Get Member By ID
    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    // Update Member
    public Member updateMember(Long id, Member updatedMember) {

        Optional<Member> existingMember = memberRepository.findById(id);

        if (existingMember.isPresent()) {

            Member member = existingMember.get();

            member.setName(updatedMember.getName());
            member.setEmail(updatedMember.getEmail());
            member.setPhone(updatedMember.getPhone());
            member.setAddress(updatedMember.getAddress());
            member.setMembershipDate(updatedMember.getMembershipDate());
            member.setStatus(updatedMember.getStatus());

            return memberRepository.save(member);
        }

        return null;
    }

    // Borrowing History
    public List<BorrowingHistoryResponse> getBorrowingHistory(Long memberId) {

        List<BookIssue> issues =
                bookIssueRepository.findByMember_Id(memberId);

        return issues.stream()
                .map(issue -> new BorrowingHistoryResponse(
                        issue.getBook().getTitle(),
                        issue.getIssueDate(),
                        issue.getDueDate(),
                        issue.getReturnDate(),
                        issue.getStatus()
                ))
                .toList();
    }
}