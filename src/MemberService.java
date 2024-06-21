import java.util.List;

public class MemberService {

    MemberDAO memberDAO = new MemberDAO();

    public void addMember(Member member) {
        memberDAO.addMember(member);
    }

    public void updateMember(Member member) {
        memberDAO.updateMember(member);
    }

    public void deleteMember(int memberId) {
        memberDAO.deleteMember(memberId);
    }

    public Member getMember(int memberId) {
        return memberDAO.getMember(memberId);
    }

    public List<Member> getAllMembers() {
        return memberDAO.getAllMembers();
    }


}
