import { useEffect, useState } from "react";
import { useLoaderData, useParams } from "react-router-dom"
import { api } from "../../api/axios";

export const ParticularUserSection = (props) => {
  // const user = useLoaderData()
  const params = useParams()
  console.log(params.userId);

  const [fetchedUser, setFetchedUser] = useState(null)

  useEffect(()=>{
    const fetchParticularUserDetails = async() => {
      try {
        console.log(`/admin/users/${params.userId}`);
        const response = await api.get(`/admin/users/${params.userId}`)
        console.log(response.data);
        setFetchedUser(response.data)
        console.log(fetchedUser)
      } catch (error) {

      }
    }
    fetchParticularUserDetails()
  },[])

  if (!fetchedUser) {
    return <div>Loading user details...</div>;
  }
  return(
    <>
      Hello User
      <p>{fetchedUser.userId}</p>
      <p>{fetchedUser.name}</p>
      <p>{fetchedUser.email}</p>
      <p>{fetchedUser.phoneNumber}</p>
      <p>{fetchedUser.role}</p>
      <p>{fetchedUser.status}</p>
      <button className="p-1.5 bg-teal-600 text-white">
        {fetchedUser.status === 'ACTIVE' ? 'SUSPEND' : 'ACTIVE'}
      </button>
    </>
  )
}