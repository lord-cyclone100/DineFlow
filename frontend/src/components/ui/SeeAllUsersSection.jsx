import { useEffect, useState } from "react"
import { api } from "../../api/axios"
import { AdminPanelUserCard } from "./AdminPanelUserCard"

export const SeeAllUsersSection = () => {
  
  const [userList, setUserList] = useState([])

  useEffect(()=>{
    const fetchAllUsers = async() => {
      try {
        const response = await api.get('/admin/users')
        console.log(response.data);
        setUserList(response.data)
      } catch (error) {
        
      }
    }
    fetchAllUsers()
  },[])
  return(
    <>
      {
        userList.map((user)=>(
          <AdminPanelUserCard value={user}/>
        ))
      }
    </>
  )
}