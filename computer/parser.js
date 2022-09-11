# Parse string into program
function parse(code: string) :: nbtcompound:
  set {_state} to nbt compound from "{}"
  set string tag "string" of {_state} to {_code}
  return Program({_state})

# Program
#   - NumberLiteral
function Program(state: nbtcompound) :: nbtcompound:
  set {_compound} to nbt compound from "{type:""program""}"
  set compound tag "body" of {_compound} to NumberLiteral({_state})
  return {_compound}

# NumberLiteral
#   - NUMBER
function NumberLiteral(state: nbtcompound) :: nbtcompound:
  set {_compound} to nbt compound from "{type:""NumberLiteral""}"
  set {_string} to string tag "string" of {_state}
  set double tag "value" of {_compound} to {_string} parsed as number
  return {_compound}
