package org.example.democonfigserver.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/git")
public class GitHookController {

    @PostMapping("/notify")
    public String gitNotify(@RequestBody Map<String, String> payload) {
        String message = payload.get("message");
        System.out.println("Git에서 변경 사항이 업로드되었습니다: " + message);
        return "업로드 완료 확인";
    }
}
