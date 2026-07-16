import { useState } from "react"
import { api } from "../../api/axios"
import { useChangePasswordToggle } from "../../store/store"

export const PasswordChangeModal = () => {

  const [newPassword, setNewPassword] = useState('')
  const { changePasswordModalShow, setChangePasswordModalShow } = useChangePasswordToggle()

  const handleChangePasswordButtonClick = async (e) => {
    e.preventDefault()
    try {
      const response = await api.patch('/auth/changepassword',{password:newPassword})
      setChangePasswordModalShow(false)
      alert(response.data);
    } catch (error) {
      console.error(error)
    }
    // alert('Password Changed')
  }

  const handleNewPasswordChange = (e) => {
    setNewPassword(e.target.value)
  }
  
  if(changePasswordModalShow){
    return(
      <>
        <form className="p-5 border-2">
          <div className="flex items-center gap-10">
            <h1>Change Password</h1>
            <button className="border p-1.5 bg-rose-700 text-white" onClick={()=>setChangePasswordModalShow(false)}>X</button>
          </div>
          <input type="text" className="border" placeholder="Enter new password" name="newPassword" onChange={handleNewPasswordChange} />
          <button className="border bg-sky-700 text-white p-2" onClick={handleChangePasswordButtonClick}>Update Password</button>
        </form>
      </>
    )
  }
}