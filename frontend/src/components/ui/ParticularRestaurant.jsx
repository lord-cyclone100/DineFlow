import { useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"
import { api } from "../../api/axios"
import { UpdateRestaurantModal } from "./UpdateRestaurantModal"
import { useUpdateRestaurantModal } from "../../store/store"

export const ParticularRestaurant = () => {
  const params = useParams()
  const navigate = useNavigate()

  const [restaurantDetails, setRestaurantDetails] = useState(null)
  const { updateRestaurantModalShow, setUpdateRestaurantModalShow } = useUpdateRestaurantModal()

  useEffect(()=>{
    const fetchParticularRestaurant = async() => {
      try {
        const response = await api.get(`/restaurants/${params.restaurantId}`)
        setRestaurantDetails(response.data)
      }
      catch (error) {
        console.error(error)
      } 
    }
    fetchParticularRestaurant()
  },[])

  const deleteRestaurant = async() => {
    if(!restaurantDetails) return;
    const response = await api.delete(`/restaurants/${params.restaurantId}`)
    alert(response.data)
    navigate('/admin')
  }

  if(!restaurantDetails){
    return <h1>Loading......</h1>
  }
  return(
    <>
      <p>{restaurantDetails.restaurantId}</p>
      <p>{restaurantDetails.name}</p>
      <p>{restaurantDetails.cuisineType}</p>
      <button className="p-1 bg-sky-700 text-white" onClick={()=>setUpdateRestaurantModalShow(true)}>Edit Restaurant Details</button>
      <button className="p-1.5 bg-rose-600 text-white" onClick={deleteRestaurant} >Delete Restaurant</button>
      <UpdateRestaurantModal value={restaurantDetails} />
    </>
  )
}