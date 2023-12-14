const hobbyEtc = document.querySelector("#hobby-etc");
hobbyEtc.addEventListener('keyup', (e) => {
    // 엔터를 누른 경우, 입력완료로 간주한다.
    if(e.keyCode === 13){
        // 자동으로 생겨난 <br><br>을 제거. blur처리
        // console.log(e.target.innerHTML); // 값<br><br>
        e.target.innerHTML = e.target.innerHTML.replace(/<br>/g, '');
        e.target.blur();
    }
});
hobbyEtc.addEventListener('blur', (e) => {
    const value = e.target.innerHTML;
    if(value && value != '직접입력') {
        const html = `
            <div class="inline-flex items-center mr-5">
              <input id="hobby-${value}" type="checkbox" name="hobby" value="${value}" checked class="w-5 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 focus:ring-2" >
              <label for="hobby-${value}" class="ms-3 text-sm font-medium text-gray-900">${value}</label>
            </div>`;
        // 특정요소기준으로 새요소 추가
        // - beforebegin 시작태그앞. 이전 형제요소로 추가
        // - afterbegin 시작태그뒤. 첫 자식요소로 추가
        // - beforeend 종료태그앞. 마지막 자식요소로 추가
        // - afterend 종료태그뒤. 다음 형제요소로 추가
        // label#hobby-etc를 감싼 div 태그
        e.target.parentElement.insertAdjacentHTML('beforebegin', html);
        e.target.innerHTML = '직접입력';
    }

});

/**
 * 회원정보 수정 유효성검사
 */
document.memberUpdateFrm.addEventListener('submit', (e) => {
    const frm = e.target;
    const name = frm.name;
    const email = frm.email;



    // 이름 한글2글자 이상
    if(!/^[가-힣]{2,}$/.test(name.value)) {
        alert('이름은 한글 2글자 이상 작성하세요.');
        e.preventDefault();
        return;
    }
    // 이메일 형식
    if(!/^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/.test(email.value)) {
        alert('유효한 이메일을 작성하세요.');
        e.preventDefault();
        return;
    }
});