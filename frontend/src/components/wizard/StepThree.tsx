import { useState } from 'react'
import { Group, FormData } from '../../utils/types.ts'
import { submitRegistration } from '../../services/api.ts'
import { buttonStyles } from '../../utils/styles.ts'

const FORM_ID = 'B171388180BC457D9887AD92B6CCFC86'

interface RowProps {
  label: string
  value: string
}

interface StepThreeProps {
  group: Group
  formData: FormData
  back: () => void
  onReset: () => void
}

function Row({ label, value }: RowProps) {
  return (
    <div className="flex justify-between text-sm">
      <span className="text-gray-500">{label}</span>
      <span className="text-gray-800 font-medium">{value}</span>
    </div>
  )
}

export default function StepThree({ group, formData, back, onReset }: StepThreeProps) {
  const [loading, setLoading] = useState<boolean>(false)
  const [error, setError] = useState<string | null>(null)
  const [success, setSuccess] = useState<boolean>(false)

  const memberType = group.memberTypes.find(mt => mt.id === formData.memberTypeId)

  const handleSubmit = async () => {
    setLoading(true)
    setError(null)
    try {
      await submitRegistration(FORM_ID, {
        memberTypeId: formData.memberTypeId,
        firstName: formData.firstName,
        lastName: formData.lastName,
        email: formData.email,
        phone: formData.phone,
        birthDate: formData.birthDate,
      })
      setSuccess(true)
    } catch (e) {
      setError('Please try again.')
    } finally {
      setLoading(false)
    }
  }

  if (success) {
    return (
      <div className="text-center py-8">
        <h2 className="text-2xl font-bold text-gray-800 mb-2">Registration Successful!</h2>
        <p className="text-gray-500">Thank you for registering for {group.title}.</p>

        <div className='mt-4 d-flex items-center pl-24 pr-24'>
          <button
            onClick={onReset}
            className={buttonStyles.primary}
          >
            Add new member
          </button>
        </div>

      </div>
    )
  }

  return (
    <div>
      <h2 className="text-xl font-bold text-gray-800 mb-6">Review</h2>

      <div className="bg-gray-50 rounded-lg p-4 space-y-3 mb-6">
        <Row label="Member Type" value={memberType?.name ?? '—'} />
        <Row label="First Name" value={formData.firstName} />
        <Row label="Last Name" value={formData.lastName} />
        <Row label="Email" value={formData.email} />
        <Row label="Phone" value={formData.phone || '—'} />
        <Row label="Birth Date" value={formData.birthDate} />
      </div>

      {error && (
        <div className="bg-red-50 border border-red-200 text-red-600 rounded-lg p-3 mb-4 text-sm">
          {error}
        </div>
      )}

      <div className="flex gap-3">
        <button onClick={back} disabled={loading} className={buttonStyles.secondary}>Back</button>
        <button onClick={handleSubmit} disabled={loading} className={buttonStyles.primary}>
          {loading ? 'Submitting...' : 'Submit'}
        </button>
      </div>
    </div>
  )
}