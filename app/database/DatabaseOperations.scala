package database

trait DatabaseOperations {
  def queryAll(): Stream[Any]

  def find(id: Long): Any
}
