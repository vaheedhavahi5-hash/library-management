package com.example.librarymanagement.controller;
import com.example.librarymanagement.entity.Member;
import com.example.librarymanagement.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.librarymanagement.entity.BorrowingHistoryResponse;

import java.util.List;

@RestController
@RequestMapping("/api/members")


public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        Member savedMember = memberService.createMember(member);
        return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(
            @PathVariable Long id,
            @RequestBody Member member) {

        Member updatedMember = memberService.updateMember(id, member);

        if (updatedMember == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedMember);
    }
    @GetMapping("/{id}/borrowing-history")
    public ResponseEntity<List<BorrowingHistoryResponse>> getBorrowingHistory(
            @PathVariable Long id) {

        List<BorrowingHistoryResponse> history =
                memberService.getBorrowingHistory(id);

        return ResponseEntity.ok(history);
    }

}
