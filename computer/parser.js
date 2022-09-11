# Parse string into program
function parse(code: string) :: nbtcompound:
  set {_state} to {_code}
  return program({_state})

# Program
#   - numberLiteral
function Program(state: string) :: nbtcompound:
  return numberLiteral({_state})

# NumberLiteral
#   - NUMBER
function NumberLiteral(state: string) :: nbtcompound:
  set {_compound} to nbt compound from "{Type:""Literal""}"
  set double tag "Value" of {_compound} to {_state} parsed as number
  return {_compound}

on load:
  broadcast parse("3")
