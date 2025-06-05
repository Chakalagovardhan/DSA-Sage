import React from 'react'
import { Routes,Router, Route } from 'react-router-dom'
import ExamPortal from '../components/ExamPortal'
import HomePage from '../components/HomePage'
const AppRouters = () => {
  return (
    <Routes>
      <Route path='/' element={<HomePage />}/>
      <Route path='/examportal' element={<ExamPortal />}/>
    </Routes>
  )
}

export default AppRouters
