/**
 *
 */
class MbtiFetch{
    constructor(baseURI){
        this.baseURI = baseURI;
        this.headers = {
            accept:"application/json"
        }
    }
    async selectList(){
        let resp = await fetch(this.baseURI, {
            headers:this.headers
        });
        if(resp.ok){
            return resp.json();
        }else{
            throw new Error(`상태코드 : ${resp.status}`);
        }
    }
    async selectOne(mtType){
        let resp = await fetch(`${this.baseURI}/${mtType}`, {
            headers:this.headers
        });
        if(resp.ok){
            return resp.json();
        }else{
            throw new Error(`상태코드 : ${resp.status}`);
        }
    }
    async insert(formData){
        let resp = await fetch(this.baseURI, {
            method:"post",
            headers:this.headers,
            body : formData
        });
        if(resp.ok){
            return resp.json();
        }else{
            throw new Error(`상태코드 : ${resp.status}`);
        }
    }
    async update(mtType, formData){
        let resp = await fetch(`${this.baseURI}/${mtType}`, {
            method:"put",
            headers:this.headers,
            body : formData
        });
        if(resp.ok){
            return resp.json();
        }else{
            throw new Error(`상태코드 : ${resp.status}`);
        }
    }
    async delete(mtType){
        let resp = await fetch(`${this.baseURI}/${mtType}`, {
            method:"delete"
            , headers:this.headers
        });
        if(resp.ok){
            return resp.json();
        }else{
            throw new Error(`상태코드 : ${resp.status}`);
        }
    }
}
document.addEventListener("DOMContentLoaded", async ()=>{
    const select = document.querySelector('[name="mbti"]');
    const modelEl = document.querySelector('#exampleModal');
    const updateForm = document.querySelector("#update-form");
    const insertForm = document.querySelector("#insert-form");
    const myModal = new bootstrap.Modal('#exampleModal');
    const delBtn = document.querySelector("#del-btn");

    let mbtiFetch = new MbtiFetch("../../mbti");

    select.addEventListener("dataload", async (e)=>{
        let {mbtiList} = await mbtiFetch.selectList();
        e.target.innerHTML = mbtiList.map(m=>`<option value="${m.mtType}">${m.mtTitle}</option>`)
            .join("\n");
    });

    select.dispatchEvent(new Event("dataload"));

//		EDD(Event-Driven-Development)
    modelEl.addEventListener("show.bs.modal", async (e)=>{
        let mtType = e.relatedTarget.value;
        let {mbti} = await mbtiFetch.selectOne(mtType);
//		updateForm.mtType = mbti.mtType;
        for(let prop in mbti){
            if(updateForm[prop])
                updateForm[prop].value = mbti[prop];
        }
    });
    modelEl.addEventListener("hidden.bs.modal", async (e)=>{
        updateForm.reset();
    });

    select.addEventListener("change", async (e)=>{
        myModal.show(e.target);
    });
    updateForm.addEventListener("submit", async (e)=>{
        e.preventDefault();
        let formData = new FormData(e.target);
        let {success} = await mbtiFetch.update(formData.get("mtType"), formData);
        if(success){
            select.dispatchEvent(new Event("dataload"));
            myModal.hide();
        }
    });
    delBtn.addEventListener("click", async (e)=>{
        let mtType = updateForm.mtType.value;
        let {success} = await mbtiFetch.delete(mtType);
        if(success){
            select.dispatchEvent(new Event("dataload"));
            myModal.hide();
        }
    });

    insertForm.addEventListener("submit", async (e)=>{
        e.preventDefault();
        let formData = new FormData(e.target);
        let {mbti} = await mbtiFetch.insert(formData);
        select.insertAdjacentHTML("beforeend", `<option value="${mbti.mtType}">${mbti.mtTitle}</option>`);
        insertForm.reset();
    });
});









