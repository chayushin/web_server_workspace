// 비밀번호 일치여부
document.querySelector("#newPasswordConfirmation").onblur = (e) => {
    const pw1 = document.querySelector("#newPassword");
    const pw2 = e.target;
    if(pw1.value !== pw2.value) {
        alert("비밀번호가 일치하지 않습니다.");
        pw1.select();
    }
};

// 폼 유효성검사
document.memberPasswordUpdateFrm.onsubmit = (e) => {
    const frm = e.target;
    const oldPassword = e.target.oldPassword;
    const newPassword = e.target.newPassword;
    const newPasswordConfirmation = e.target.querySelector("#newPasswordConfirmation");

    // 비밀번호 검사 - 영문자/숫자/특수문자!@#$% 4글자 이상
    // 비밀번호 - 영문자/숫자/특수문자!@#$% 4글자이상
    const regExps = [
        {
            re: /[A-Za-z]/,
            msg: '비밀번호는 영문자를 하나이상 포함해주세요'
        },
        {
            re:/\d/,
            msg: '비밀번호는 숫자를 하나이상 포함해주세요'
        },
        {
            re: /[!@#$%]/,
            msg: '비밀번호는 특수문자!@#$% 중에 하나를 포함해주세요'
        },
        {
            re: /^.{4,}$/,
            mgs: '비밀번호는 4글자 이상 작성해주세요.'
        }
    ];
    for(let i = 0; i < regExps.length; i++){
        const {re, msg} = regExps[i];
        if(!re.test(newPassword.value)) {
            alert(msg);
            e.preventDefault();
            return;
        }
    }
    if (newPassword.value !== newPasswordConfirmation.value) {
        alert("두 비밀번호가 일치하지 않습니다.");
        return false;
    }
};