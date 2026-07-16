import { useContext } from "react"
import { AuthContext } from "../auth/AuthContext"
import { PasswordChangeModal } from "../components/ui/PasswordChangeModal";
import { useChangePasswordToggle } from "../store/store";

export const ProfilePage = () => {

  const { logout } = useContext(AuthContext)
  const { setChangePasswordModalShow } = useChangePasswordToggle()

  const handleLogout = () => {
    logout()
  }

  const { currentUser } = useContext(AuthContext)
  console.log(currentUser);
  return(
    <>
      <div>
        <h1>Profile Page</h1>
        <p>{currentUser.name}</p>
        <p>{currentUser.email}</p>
        <p>{currentUser.phoneNumber}</p>
        <p>{currentUser.userId}</p>

        <button className="border bg-sky-700 text-white p-2" onClick={()=>setChangePasswordModalShow(true)}>Change Password</button>
        <button className="border bg-rose-700 text-white p-2" onClick={handleLogout}>Logout</button>
        <PasswordChangeModal />
      </div>
    </>
  )
}